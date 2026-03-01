package com.app.trycatch.mybatis.converter;


import com.app.trycatch.common.enumeration.member.Status;
import com.app.trycatch.common.enumeration.report.ReportStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToReportStatusConverter implements Converter<String, ReportStatus> {
    @Override
    public ReportStatus convert(String source) {
        return ReportStatus.getReportStatus(source);
    }
}
