package com.app.trycatch.repository.skilllog;

import com.app.trycatch.domain.skilllog.SkillLogCommentLikesVO;
import com.app.trycatch.domain.skilllog.SkillLogLikesVO;
import com.app.trycatch.mapper.skilllog.SkillLogCommentLikesMapper;
import com.app.trycatch.mapper.skilllog.SkillLogLikesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SkillLogCommentLikesDAO {
    private final SkillLogCommentLikesMapper skillLogCommentLikesMapper;

//    좋아요 개수
    public int findCountBySkillLogCommentId(Long id) {
        return skillLogCommentLikesMapper.selectCountBySkillLogCommentId(id);
    }

//    조회
    public Optional<SkillLogCommentLikesVO> findBySkillLogCommentIdAndMemberId(SkillLogCommentLikesVO skillLogCommentLikesVO) {
        return skillLogCommentLikesMapper.selectBySkillLogCommentIdAndMemberId(skillLogCommentLikesVO);
    }

//    추가
    public void save(SkillLogCommentLikesVO skillLogCommentLikesVO) {
        skillLogCommentLikesMapper.insert(skillLogCommentLikesVO);
    }
//    삭제
    public void delete(Long id){
        skillLogCommentLikesMapper.delete(id);
    }
    public void deleteBySkillLogCommentId(Long id) {
        skillLogCommentLikesMapper.deleteBySkillLogCommentId(id);
    }
    public void deleteBySkillLogId(Long id) {
        skillLogCommentLikesMapper.deleteBySkillLogId(id);
    }
}
