package com.app.trycatch.repository.skilllog;

import com.app.trycatch.domain.skilllog.SkillLogLikesVO;
import com.app.trycatch.mapper.skilllog.SkillLogLikesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SkillLogLikesDAO {
    private final SkillLogLikesMapper skillLogLikesMapper;

//    좋아요 개수
    public int findCountBySkillLogId(Long id) {
        return skillLogLikesMapper.selectCountBySkillLogId(id);
    }

//    조회
    public Optional<SkillLogLikesVO> findBySkillLogIdAndMemberId(SkillLogLikesVO skillLogLikesVO) {
        return skillLogLikesMapper.selectBySkillLogIdAndMemberId(skillLogLikesVO);
    }

//    추가
    public void save(SkillLogLikesVO skillLogLikesVO) {
        skillLogLikesMapper.insert(skillLogLikesVO);
    }
//    삭제
    public void delete(Long id){
        skillLogLikesMapper.delete(id);
    }
    public void deleteBySkillLogId(Long id) {
        skillLogLikesMapper.deleteBySkillLogId(id);
    }

}
