package com.app.trycatch.mapper;

import com.app.trycatch.domain.skilllog.SkillLogCommentReportVO;
import com.app.trycatch.dto.report.ReportDTO;
import com.app.trycatch.dto.skilllog.SkillLogCommentReportDTO;
import com.app.trycatch.dto.skilllog.SkillLogReportDTO;
import com.app.trycatch.mapper.report.ReportMapper;
import com.app.trycatch.mapper.skilllog.SkillLogCommentReportMapper;
import com.app.trycatch.mapper.skilllog.SkillLogReportMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SkillLogCommentReportMapperTests {
    @Autowired
    private SkillLogCommentReportMapper skillLogCommentReportMapper;
    @Autowired
    private ReportMapper reportMapper;

    @Test
    public void testInsert() {
        ReportDTO reportDTO = new ReportDTO();
        SkillLogCommentReportDTO skillLogCommentReportDTO = new SkillLogCommentReportDTO();

        reportDTO.setMemberId(6L);
        reportDTO.setReportReasonCode(1);
        reportDTO.setReportReasonDetail("신고 사유");

        reportMapper.insert(reportDTO);

        skillLogCommentReportDTO.setId(reportDTO.getId());
        skillLogCommentReportDTO.setSkillLogCommentId(271L);

        skillLogCommentReportMapper.insert(skillLogCommentReportDTO.toSkillLogCommentReportVO());
    }
}
