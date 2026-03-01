package com.app.trycatch.service.point;

import com.app.trycatch.common.pagination.Criteria;
import com.app.trycatch.common.enumeration.point.PointType;
import com.app.trycatch.domain.point.PointDetailsVO;
import com.app.trycatch.dto.point.PointChargeRequestDTO;
import com.app.trycatch.dto.point.PointDetailsDTO;
import com.app.trycatch.dto.point.PointDetailsWithPagingDTO;
import com.app.trycatch.dto.point.PointOperationResultDTO;
import com.app.trycatch.repository.point.PointDetailsDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class PointService {
    private final PointDetailsDAO pointDetailsDAO;

    // 포인트 내역 목록 조회 (페이징)
    @Transactional(readOnly = true)
    public PointDetailsWithPagingDTO getPointDetails(Long individualMemberId, int page) {
        int total = pointDetailsDAO.findCountByIndividualMemberId(individualMemberId);
        Criteria criteria = new Criteria(page, total);

        List<PointDetailsDTO> list = pointDetailsDAO.findAllByIndividualMemberIdWithPaging(individualMemberId, criteria);
        criteria.setHasMore(list.size() == criteria.getCount());
        if (criteria.isHasMore()) list.remove(list.size() - 1);

        PointDetailsWithPagingDTO result = new PointDetailsWithPagingDTO();
        result.setPointDetails(list);
        result.setCriteria(criteria);
        return result;
    }

    // 포인트 충전
    public PointOperationResultDTO chargePoint(Long individualMemberId, PointChargeRequestDTO requestDTO) {
        PointOperationResultDTO resultDTO = new PointOperationResultDTO();

        if (requestDTO == null || requestDTO.getPointAmount() <= 0) {
            resultDTO.setSuccess(false);
            resultDTO.setMessage("충전할 별 개수를 확인해주세요.");
            return resultDTO;
        }

        if (requestDTO.getPaymentOrderId() == null || requestDTO.getPaymentOrderId().isBlank()) {
            resultDTO.setSuccess(false);
            resultDTO.setMessage("결제 주문번호가 없습니다.");
            return resultDTO;
        }

        int expectedPaymentAmount = requestDTO.getPointAmount() * 110;
        if (requestDTO.getPaymentAmount() != expectedPaymentAmount) {
            resultDTO.setSuccess(false);
            resultDTO.setMessage("결제 금액 검증에 실패했습니다.");
            return resultDTO;
        }

        if (pointDetailsDAO.findEarnCountByPaymentOrderId(requestDTO.getPaymentOrderId()) > 0) {
            resultDTO.setSuccess(false);
            resultDTO.setMessage("이미 처리된 결제입니다.");
            return resultDTO;
        }

        pointDetailsDAO.save(requestDTO.toVO(individualMemberId));

        int updatedCount = pointDetailsDAO.setIndividualMemberPointDelta(individualMemberId, requestDTO.getPointAmount());
        if (updatedCount <= 0) {
            throw new IllegalStateException("회원 포인트 충전에 실패했습니다.");
        }

        resultDTO.setSuccess(true);
        resultDTO.setMessage("별이 충전되었습니다.");
        resultDTO.setCurrentPoint(pointDetailsDAO.findCurrentPointByIndividualMemberId(individualMemberId));
        return resultDTO;
    }

    // 포인트 사용 (QnA 글 작성 시 차감)
    public void usePoint(Long individualMemberId, int amount) {
        int currentPoint = pointDetailsDAO.findCurrentPointByIndividualMemberId(individualMemberId);
        if (currentPoint < amount) {
            throw new IllegalStateException("보유 별이 부족합니다.");
        }

        // USE 내역 추가 (잔여수량 = 사용 후 보유 포인트)
        PointDetailsVO useVO = PointDetailsVO.builder()
                .individualMemberId(individualMemberId)
                .pointType(PointType.USE)
                .pointAmount(-amount)
                .remainingPointAmount(currentPoint - amount)
                .paymentAmount(0)
                .build();
        pointDetailsDAO.save(useVO);

        // 보유 포인트 차감
        pointDetailsDAO.setIndividualMemberPointDelta(individualMemberId, -amount);

        // EARN 잔여수량 차감 (오래된 충전부터)
        List<PointDetailsDTO> earnDetails = pointDetailsDAO.findEarnDetailsWithRemaining(individualMemberId);
        int remaining = amount;
        for (PointDetailsDTO earn : earnDetails) {
            if (remaining <= 0) break;
            int deduct = Math.min(remaining, earn.getRemainingPointAmount());
            pointDetailsDAO.setRemainingPointAmount(earn.getId(), earn.getRemainingPointAmount() - deduct);
            remaining -= deduct;
        }
    }

    // 포인트 충전 취소
    public PointOperationResultDTO cancelPointCharge(Long individualMemberId, Long pointDetailId) {
        PointOperationResultDTO resultDTO = new PointOperationResultDTO();

        if (pointDetailId == null) {
            resultDTO.setSuccess(false);
            resultDTO.setMessage("취소할 결제 내역이 없습니다.");
            return resultDTO;
        }

        PointDetailsDTO pointDetails = pointDetailsDAO.findChargeDetailByIdAndIndividualMemberId(
                        pointDetailId, individualMemberId)
                .orElse(null);

        if (pointDetails == null) {
            resultDTO.setSuccess(false);
            resultDTO.setMessage("취소 대상 결제를 찾을 수 없습니다.");
            return resultDTO;
        }

        if (!pointDetails.isCancellable()) {
            resultDTO.setSuccess(false);
            resultDTO.setMessage("취소할 수 없는 결제입니다.");
            return resultDTO;
        }

        int currentPoint = pointDetailsDAO.findCurrentPointByIndividualMemberId(individualMemberId);
        if (currentPoint < pointDetails.getPointAmount()) {
            resultDTO.setSuccess(false);
            resultDTO.setMessage("이미 사용된 별이 있어 구매취소할 수 없습니다.");
            return resultDTO;
        }

        int decrementedCount = pointDetailsDAO.setIndividualMemberPointDelta(individualMemberId, -pointDetails.getPointAmount());
        if (decrementedCount <= 0) {
            resultDTO.setSuccess(false);
            resultDTO.setMessage("보유 별이 부족하여 취소할 수 없습니다.");
            return resultDTO;
        }

        int cancelledCount = pointDetailsDAO.setChargeCancelled(pointDetails.getId(), individualMemberId);
        if (cancelledCount <= 0) {
            throw new IllegalStateException("결제 취소 반영에 실패했습니다.");
        }

        int remainingAfterCancel = currentPoint - pointDetails.getPointAmount();
        pointDetailsDAO.save(pointDetails.toCancelVO(individualMemberId, remainingAfterCancel));

        resultDTO.setSuccess(true);
        resultDTO.setMessage("구매취소가 완료되었습니다.");
        resultDTO.setCurrentPoint(pointDetailsDAO.findCurrentPointByIndividualMemberId(individualMemberId));
        return resultDTO;
    }

}
