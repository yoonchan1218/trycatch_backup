package com.app.trycatch.repository.report;

import com.app.trycatch.dto.report.ReportDTO;
import com.app.trycatch.mapper.report.ReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReportDAO {
    private final ReportMapper reportMapper;

    public void save(ReportDTO reportDTO) {
        reportMapper.insert(reportDTO);
    }
}
