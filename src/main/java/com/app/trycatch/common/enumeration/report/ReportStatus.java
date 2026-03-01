package com.app.trycatch.common.enumeration.report;

import com.app.trycatch.common.enumeration.skillLog.SkillLogStatus;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ReportStatus {
    PENDING("pending"), REVIEWING("reviewing"), PROCESSED("processed"), REJECTED("rejected");

    private String value;
    private static final Map<String, ReportStatus> REPORT_STATUS_MAP = Arrays.stream(ReportStatus.values())
            .collect(Collectors.toMap(ReportStatus::getValue, Function.identity()));

    private ReportStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    public static ReportStatus getReportStatus(String value) {
        return REPORT_STATUS_MAP.get(value);
    }
}
