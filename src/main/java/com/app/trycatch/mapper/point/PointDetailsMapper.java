package com.app.trycatch.mapper.point;

import com.app.trycatch.common.pagination.Criteria;
import com.app.trycatch.domain.point.PointDetailsVO;
import com.app.trycatch.dto.point.PointDetailsDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PointDetailsMapper {
//    건수 조회
    int selectCountByIndividualMemberId(Long individualMemberId);
//    목록 (페이징)
    List<PointDetailsDTO> selectAllByIndividualMemberIdWithPaging(@Param("individualMemberId") Long individualMemberId, @Param("criteria") Criteria criteria);
//    추가
    void insert(PointDetailsVO pointDetailsVO);

    int selectEarnCountByPaymentOrderId(String paymentOrderId);

    int updateIndividualMemberPointByDelta(@Param("individualMemberId") Long individualMemberId, @Param("delta") int delta);

    Integer selectCurrentPointByIndividualMemberId(Long individualMemberId);

    Optional<PointDetailsDTO> selectChargeDetailByIdAndIndividualMemberId(@Param("id") Long id, @Param("individualMemberId") Long individualMemberId);

    int updateChargeCancelled(@Param("id") Long id, @Param("individualMemberId") Long individualMemberId);

    List<PointDetailsDTO> selectEarnDetailsWithRemaining(Long individualMemberId);

    int updateRemainingPointAmount(@Param("id") Long id, @Param("remainingPointAmount") int remainingPointAmount);
}
