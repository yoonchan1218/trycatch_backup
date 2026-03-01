package com.app.trycatch.mapper;

import com.app.trycatch.common.enumeration.skillLog.SkillLogReportTargetType;
import com.app.trycatch.domain.skilllog.SkillLogReportVO;
import com.app.trycatch.dto.report.ReportDTO;
import com.app.trycatch.dto.skilllog.SkillLogReportDTO;
import com.app.trycatch.mapper.report.ReportMapper;
import com.app.trycatch.mapper.skilllog.SkillLogReportMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SkillLogReportMapperTests {
    @Autowired
    private SkillLogReportMapper skillLogReportMapper;
    @Autowired
    private ReportMapper reportMapper;

    @Test
    public void testInsert() {
        ReportDTO reportDTO = new ReportDTO();
        SkillLogReportDTO skillLogReportDTO = new SkillLogReportDTO();

        reportDTO.setMemberId(6L);
        reportDTO.setReportReasonCode(1);
        reportDTO.setReportReasonDetail("신고 사유");

        reportMapper.insert(reportDTO);

        skillLogReportDTO.setId(reportDTO.getId());
        skillLogReportDTO.setSkillLogId(30L);

        skillLogReportMapper.insert(skillLogReportDTO.toSkillLogReportVO());
    }
}
