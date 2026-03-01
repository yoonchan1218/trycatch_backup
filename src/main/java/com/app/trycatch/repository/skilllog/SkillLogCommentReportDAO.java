package com.app.trycatch.repository.skilllog;

import com.app.trycatch.domain.skilllog.SkillLogCommentReportVO;
import com.app.trycatch.domain.skilllog.SkillLogReportVO;
import com.app.trycatch.mapper.skilllog.SkillLogCommentReportMapper;
import com.app.trycatch.mapper.skilllog.SkillLogReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SkillLogCommentReportDAO {
    private final SkillLogCommentReportMapper skillLogCommentReportMapper;

    public void save(SkillLogCommentReportVO skillLogCommentReportVO) {
        skillLogCommentReportMapper.insert(skillLogCommentReportVO);
    }
}
