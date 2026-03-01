package com.app.trycatch.service;

import com.app.trycatch.domain.skilllog.SkillLogCommentReportVO;
import com.app.trycatch.dto.skilllog.SkillLogCommentReportDTO;
import com.app.trycatch.dto.skilllog.SkillLogReportDTO;
import com.app.trycatch.service.report.ReportService;
import com.app.trycatch.service.skilllog.SkillLogService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ReportServiceTests {
    @Autowired
    private ReportService reportService;

    @Test
    public void testSkillLogReport() {
        SkillLogReportDTO skillLogReportDTO = new SkillLogReportDTO();

        skillLogReportDTO.setMemberId(6L);
        skillLogReportDTO.setReportReasonCode(1);
        skillLogReportDTO.setReportReasonDetail("불건전한 내용");
        skillLogReportDTO.setSkillLogId(30L);

        reportService.skillLogReport(skillLogReportDTO);
    }
    @Test
    public void testReport() {
        SkillLogCommentReportDTO skillLogCommentReportDTO = new SkillLogCommentReportDTO();

        skillLogCommentReportDTO.setMemberId(6L);
        skillLogCommentReportDTO.setReportReasonCode(1);
        skillLogCommentReportDTO.setReportReasonDetail("불건전한 내용");
        skillLogCommentReportDTO.setSkillLogCommentId(271L);

        reportService.skillLogCommentReport(skillLogCommentReportDTO);
    }
}
