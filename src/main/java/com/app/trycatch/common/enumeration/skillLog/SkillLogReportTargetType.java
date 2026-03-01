package com.app.trycatch.common.enumeration.skillLog;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum SkillLogReportTargetType {
    LOG("log"), COMMENT("comment");

    private String value;
    private static final Map<String, SkillLogReportTargetType> SKILL_LOG_REPORT_TARGET_TYPE_MAP = Arrays.stream(SkillLogReportTargetType.values())
            .collect(Collectors.toMap(SkillLogReportTargetType::getValue, Function.identity()));

    private SkillLogReportTargetType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    public static SkillLogReportTargetType getSkillLogReportTargetType(String value) {
        return SKILL_LOG_REPORT_TARGET_TYPE_MAP.get(value);
    }
}
