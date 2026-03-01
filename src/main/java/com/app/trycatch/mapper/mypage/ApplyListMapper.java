package com.app.trycatch.mapper.mypage;

import com.app.trycatch.common.pagination.Criteria;
import com.app.trycatch.dto.mypage.ApplyListDTO;
import com.app.trycatch.dto.mypage.ApplyListWithPagingDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ApplyListMapper {
    List<ApplyListDTO> selectAllByMemberId(@Param("memberId") Long memberId);

    List<ApplyListDTO> selectAllByMemberIdWithFilter(
            @Param("memberId") Long memberId,
            @Param("fromDt") String fromDt,
            @Param("toDt") String toDt,
            @Param("programStatus") String programStatus,
            @Param("applyStatus") String applyStatus,
            @Param("keyword") String keyword);

    List<ApplyListDTO> selectAllByMemberIdWithFilterAndPaging(
            @Param("memberId") Long memberId,
            @Param("fromDt") String fromDt,
            @Param("toDt") String toDt,
            @Param("programStatus") String programStatus,
            @Param("applyStatus") String applyStatus,
            @Param("keyword") String keyword,
            @Param("criteria") Criteria criteria);

    int selectCountByMemberIdWithFilter(
            @Param("memberId") Long memberId,
            @Param("fromDt") String fromDt,
            @Param("toDt") String toDt,
            @Param("programStatus") String programStatus,
            @Param("applyStatus") String applyStatus,
            @Param("keyword") String keyword);

    ApplyListWithPagingDTO selectStatusCountsByMemberIdWithFilter(
            @Param("memberId") Long memberId,
            @Param("fromDt") String fromDt,
            @Param("toDt") String toDt,
            @Param("programStatus") String programStatus,
            @Param("applyStatus") String applyStatus,
            @Param("keyword") String keyword);
}
