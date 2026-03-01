package com.app.trycatch.repository.skilllog;

import com.app.trycatch.domain.skilllog.SkillLogReportVO;
import com.app.trycatch.mapper.skilllog.SkillLogReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SkillLogReportDAO {
    private final SkillLogReportMapper skillLogReportMapper;

    public void save(SkillLogReportVO skillLogReportVO) {
        skillLogReportMapper.insert(skillLogReportVO);
    }
}
