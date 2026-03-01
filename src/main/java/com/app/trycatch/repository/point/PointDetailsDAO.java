package com.app.trycatch.repository.point;

import com.app.trycatch.common.pagination.Criteria;
import com.app.trycatch.domain.point.PointDetailsVO;
import com.app.trycatch.dto.point.PointDetailsDTO;
import com.app.trycatch.mapper.point.PointDetailsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PointDetailsDAO {
    private final PointDetailsMapper pointDetailsMapper;

    // 포인트 내역 건수 조회
    public int findCountByIndividualMemberId(Long individualMemberId) {
        return pointDetailsMapper.selectCountByIndividualMemberId(individualMemberId);
    }

    // 포인트 내역 목록 조회 (페이징)
    public List<PointDetailsDTO> findAllByIndividualMemberIdWithPaging(Long individualMemberId, Criteria criteria) {
        return pointDetailsMapper.selectAllByIndividualMemberIdWithPaging(individualMemberId, criteria);
    }

    // 포인트 내역 추가
    public void save(PointDetailsVO pointDetailsVO) {
        pointDetailsMapper.insert(pointDetailsVO);
    }

    // 동일 주문번호 충전 건수 조회
    public int findEarnCountByPaymentOrderId(String paymentOrderId) {
        return pointDetailsMapper.selectEarnCountByPaymentOrderId(paymentOrderId);
    }

    // 개인회원 보유 포인트 증감
    public int setIndividualMemberPointDelta(Long individualMemberId, int delta) {
        return pointDetailsMapper.updateIndividualMemberPointByDelta(individualMemberId, delta);
    }

    // 개인회원 현재 보유 포인트 조회
    public int findCurrentPointByIndividualMemberId(Long individualMemberId) {
        Integer currentPoint = pointDetailsMapper.selectCurrentPointByIndividualMemberId(individualMemberId);
        return currentPoint == null ? 0 : currentPoint;
    }

    // 충전 상세 조회 (취소 대상 검증용)
    public Optional<PointDetailsDTO> findChargeDetailByIdAndIndividualMemberId(Long id, Long individualMemberId) {
        return pointDetailsMapper.selectChargeDetailByIdAndIndividualMemberId(id, individualMemberId);
    }

    // 충전 건 취소 처리
    public int setChargeCancelled(Long id, Long individualMemberId) {
        return pointDetailsMapper.updateChargeCancelled(id, individualMemberId);
    }

    // 잔여수량이 남은 충전 내역 조회 (오래된 순)
    public List<PointDetailsDTO> findEarnDetailsWithRemaining(Long individualMemberId) {
        return pointDetailsMapper.selectEarnDetailsWithRemaining(individualMemberId);
    }

    // 충전 내역 잔여수량 갱신
    public int setRemainingPointAmount(Long id, int remainingPointAmount) {
        return pointDetailsMapper.updateRemainingPointAmount(id, remainingPointAmount);
    }
}
