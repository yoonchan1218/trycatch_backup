package com.app.trycatch.mapper.experience;

import com.app.trycatch.common.pagination.Criteria;
import com.app.trycatch.dto.experience.ChallengerDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ChallengerMapper {
//    참여자 목록 (뷰 사용)
    List<ChallengerDTO> selectByProgramId(@Param("programId") Long programId,
                                          @Param("status") String status,
                                          @Param("criteria") Criteria criteria);

//    참여자 수 (뷰 사용)
    int selectCountByProgramId(@Param("programId") Long programId,
                               @Param("status") String status);

//    상태별 참여자 수
    Map<String, Long> selectStatusCountByProgramId(@Param("programId") Long programId);

//    참여자 생성 (INSERT IGNORE)
    void insert(@Param("applyId") Long applyId);

//    apply_id로 challenger id 조회
    Long selectIdByApplyId(@Param("applyId") Long applyId);

//    상태 변경 (apply_id 기준)
    void updateStatus(@Param("applyId") Long applyId, @Param("challengerStatus") String challengerStatus);
}
