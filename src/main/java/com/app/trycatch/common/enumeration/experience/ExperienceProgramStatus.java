package com.app.trycatch.common.enumeration.experience;

import com.app.trycatch.common.enumeration.skillLog.SkillLogStatus;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ExperienceProgramStatus {
    DRAFT("draft"), RECRUITING("recruiting"), CLOSED("closed"), CANCELLED("cancelled");

    private String value;
    private static final Map<String, ExperienceProgramStatus> EXPERIENCE_PROGRAM_STATUS_MAP = Arrays.stream(ExperienceProgramStatus.values())
            .collect(Collectors.toMap(ExperienceProgramStatus::getValue, Function.identity()));

    private ExperienceProgramStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    public static ExperienceProgramStatus getExperienceProgramStatus(String value) {
        return EXPERIENCE_PROGRAM_STATUS_MAP.get(value);
    }
}
