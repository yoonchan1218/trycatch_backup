package com.app.trycatch.mapper.report;

import com.app.trycatch.dto.report.ReportDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReportMapper {
//    추가
    public void insert(ReportDTO reportDTO);
}
