package com.app.trycatch.mapper.skilllog;

import com.app.trycatch.domain.skilllog.SkillLogLikesVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface SkillLogLikesMapper {
//    좋아요 개수
    public int selectCountBySkillLogId(Long id);

//    조회
    public Optional<SkillLogLikesVO> selectBySkillLogIdAndMemberId(SkillLogLikesVO skillLogLikesVO);

//    추가
    public void insert(SkillLogLikesVO skillLogLikesVO);
//    삭제
    public void delete(Long id);
    public void deleteBySkillLogId(Long id);
}
