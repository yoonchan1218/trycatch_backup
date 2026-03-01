package com.app.trycatch.common.enumeration.experience;

import com.app.trycatch.common.enumeration.skillLog.SkillLogStatus;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ApplyStatus {
    APPLIED("applied"), DOCUMENT_PASS("document_pass"), DOCUMENT_FAIL("document_fail"), ACTIVITY_DONE("activity_done"), CANCELLED("cancelled");

    private String value;
    private static final Map<String, ApplyStatus> APPLY_STATUS_MAP = Arrays.stream(ApplyStatus.values())
            .collect(Collectors.toMap(ApplyStatus::getValue, Function.identity()));

    private ApplyStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    public static ApplyStatus getApplyStatus(String value) {
        return APPLY_STATUS_MAP.get(value);
    }
}
