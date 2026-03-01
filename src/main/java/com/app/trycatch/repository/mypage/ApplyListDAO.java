package com.app.trycatch.repository.mypage;

import com.app.trycatch.common.pagination.Criteria;
import com.app.trycatch.dto.mypage.ApplyListDTO;
import com.app.trycatch.dto.mypage.ApplyListWithPagingDTO;
import com.app.trycatch.mapper.mypage.ApplyListMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ApplyListDAO {
    private final ApplyListMapper applyListMapper;

    public List<ApplyListDTO> findAllByMemberId(Long memberId) {
        return applyListMapper.selectAllByMemberId(memberId);
    }

    public List<ApplyListDTO> findAllByMemberIdWithFilter(
            Long memberId, String fromDt, String toDt,
            String programStatus, String applyStatus, String keyword) {
        return applyListMapper.selectAllByMemberIdWithFilter(
                memberId, fromDt, toDt, programStatus, applyStatus, keyword);
    }

    public List<ApplyListDTO> findAllByMemberIdWithFilterAndPaging(
            Long memberId, String fromDt, String toDt,
            String programStatus, String applyStatus, String keyword,
            Criteria criteria) {
        return applyListMapper.selectAllByMemberIdWithFilterAndPaging(
                memberId, fromDt, toDt, programStatus, applyStatus, keyword, criteria);
    }

    public int findCountByMemberIdWithFilter(
            Long memberId, String fromDt, String toDt,
            String programStatus, String applyStatus, String keyword) {
        return applyListMapper.selectCountByMemberIdWithFilter(
                memberId, fromDt, toDt, programStatus, applyStatus, keyword);
    }

    public ApplyListWithPagingDTO findStatusCountsByMemberIdWithFilter(
            Long memberId, String fromDt, String toDt,
            String programStatus, String applyStatus, String keyword) {
        return applyListMapper.selectStatusCountsByMemberIdWithFilter(
                memberId, fromDt, toDt, programStatus, applyStatus, keyword);
    }
}
