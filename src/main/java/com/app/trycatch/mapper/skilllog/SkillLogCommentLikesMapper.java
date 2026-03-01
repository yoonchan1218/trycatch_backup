package com.app.trycatch.mapper.skilllog;

import com.app.trycatch.domain.skilllog.SkillLogCommentLikesVO;
import com.app.trycatch.domain.skilllog.SkillLogLikesVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface SkillLogCommentLikesMapper {
//    좋아요 개수
    public int selectCountBySkillLogCommentId(Long id);

//    조회
    public Optional<SkillLogCommentLikesVO> selectBySkillLogCommentIdAndMemberId(SkillLogCommentLikesVO skillLogCommentLikesVO);

//    추가
    public void insert(SkillLogCommentLikesVO skillLogCommentLikesVO);
//    삭제
    public void delete(Long id);
    public void deleteBySkillLogCommentId(Long id);
    public void deleteBySkillLogId(Long id);
}
