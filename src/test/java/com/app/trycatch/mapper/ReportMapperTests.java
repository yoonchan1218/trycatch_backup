package com.app.trycatch.mapper;

import com.app.trycatch.dto.report.ReportDTO;
import com.app.trycatch.mapper.report.ReportMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ReportMapperTests {
    @Autowired
    private ReportMapper reportMapper;

    @Test
    public void testInsert() {
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setMemberId(6L);
        reportDTO.setReportReasonCode(2);
        reportDTO.setReportReasonDetail("신고 사유");

        reportMapper.insert(reportDTO);
        log.info("{}", reportDTO.getId());
    }
}
