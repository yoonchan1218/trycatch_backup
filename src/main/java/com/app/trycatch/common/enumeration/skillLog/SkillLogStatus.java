package com.app.trycatch.common.enumeration.skillLog;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum SkillLogStatus {
    PUBLISHED("published"), DELETED("deleted");

    private String value;
    private static final Map<String, SkillLogStatus> SKILL_LOG_STATUS_MAP = Arrays.stream(SkillLogStatus.values())
            .collect(Collectors.toMap(SkillLogStatus::getValue, Function.identity()));

    private SkillLogStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    public static SkillLogStatus getSkillLogStatus(String value) {
        return SKILL_LOG_STATUS_MAP.get(value);
    }
}
