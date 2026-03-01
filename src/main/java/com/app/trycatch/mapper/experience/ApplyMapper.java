package com.app.trycatch.mapper.experience;

import com.app.trycatch.common.pagination.Criteria;
import com.app.trycatch.domain.experience.ApplyVO;
import com.app.trycatch.dto.experience.ApplyDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ApplyMapper {
    void insert(ApplyVO applyVO);

    int selectExistsByProgramIdAndMemberId(@Param("programId") Long programId,
                                           @Param("memberId") Long memberId);
//    지원자 목록 (뷰 사용)
    List<ApplyDTO> selectByProgramId(@Param("programId") Long programId,
                                     @Param("status") String status,
                                     @Param("keyword") String keyword,
                                     @Param("education") String education,
                                     @Param("gender") String gender,
                                     @Param("criteria") Criteria criteria);

//    지원자 수
    int selectCountByProgramId(@Param("programId") Long programId,
                               @Param("status") String status,
                               @Param("keyword") String keyword,
                               @Param("education") String education,
                               @Param("gender") String gender);

//    상태별 지원자 수
    Map<String, Long> selectStatusCountByProgramId(@Param("programId") Long programId);

//    상태 변경
    void updateStatus(@Param("id") Long id, @Param("applyStatus") String applyStatus);
}
