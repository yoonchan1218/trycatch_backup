package com.app.trycatch.mapper.skilllog;

import com.app.trycatch.domain.skilllog.SkillLogFileVO;
import com.app.trycatch.dto.skilllog.SkillLogFileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SkillLogFileMapper {
//    추가
    public void insert(SkillLogFileVO skillLogFileVO);

//    목록
    public List<SkillLogFileDTO> selectAllBySkillLogId(Long id);

//    삭제
    public void delete(Long id);
}
