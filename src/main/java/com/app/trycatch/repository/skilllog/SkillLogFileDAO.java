package com.app.trycatch.repository.skilllog;

import com.app.trycatch.domain.skilllog.SkillLogFileVO;
import com.app.trycatch.dto.skilllog.SkillLogDTO;
import com.app.trycatch.dto.skilllog.SkillLogFileDTO;
import com.app.trycatch.mapper.skilllog.SkillLogFileMapper;
import com.app.trycatch.mapper.skilllog.SkillLogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SkillLogFileDAO {
    private final SkillLogFileMapper skillLogFileMapper;

//    추가
    public void save(SkillLogFileVO skillLogFileVO) {
        skillLogFileMapper.insert(skillLogFileVO);
    }

//    목록
    public List<SkillLogFileDTO> findAllBySkillLogId(Long id) {
        return skillLogFileMapper.selectAllBySkillLogId(id);
    }

//    삭제
    public void delete(Long id) {
        skillLogFileMapper.delete(id);
    }
}
