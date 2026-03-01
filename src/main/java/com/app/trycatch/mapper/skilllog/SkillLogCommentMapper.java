package com.app.trycatch.mapper.skilllog;

import com.app.trycatch.common.pagination.Criteria;
import com.app.trycatch.domain.skilllog.SkillLogCommentFileVO;
import com.app.trycatch.domain.skilllog.SkillLogCommentVO;
import com.app.trycatch.domain.skilllog.SkillLogFileVO;
import com.app.trycatch.dto.skilllog.SkillLogCommentDTO;
import com.app.trycatch.dto.skilllog.SkillLogFileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SkillLogCommentMapper {
//    추가
    public void insert(SkillLogCommentDTO skillLogCommentDTO);

//    목록
    //    댓글 목록
    public List<SkillLogCommentDTO> selectAllBySkillLogId(@Param("criteria") Criteria criteria, @Param("id") Long id);
    //    댓글 전체 개수
    public int selectCountAllBySkillLogId(Long id);

    //    답글 목록
    public List<SkillLogCommentDTO> selectAllByCommentParentIdAndSkillLogId(
            @Param("criteria") Criteria criteria, @Param("skillLogId") Long skillLogId, @Param("commentParentId") Long commentParentId);
    //    답글 전체 개수
    public int selectCountAllByCommentParentIdAndSkillLogId(@Param("skillLogId") Long skillLogId, @Param("commentParentId") Long commentParentId);

//    수정
    public void update(SkillLogCommentVO skillLogCommentVO);

//    삭제
    public void delete(Long id);
//    public void deleteAllBySkillLogId(Long id);
    public void deleteAllByCommentParentId(Long id);

    public void deleteNestedCommentsBySkillLogId(Long id);
    public void deleteParentCommentsBySkillLogId(Long id);

//    댓글 작성자 member_id 조회
    public Long selectMemberIdById(Long id);
}
