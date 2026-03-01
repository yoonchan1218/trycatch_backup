package com.app.trycatch.common.enumeration.experience;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ChallengerStatus {
    IN_PROGRESS("in_progress"), COMPLETED("completed"), OUT_OF_PROCESS("out_of_process"), STEP_FAILED("step_failed");

    private String value;
    private static final Map<String, ChallengerStatus> CALLENCHALLENGER_STATUS_MAP = Arrays.stream(ChallengerStatus.values())
            .collect(Collectors.toMap(ChallengerStatus::getValue, Function.identity()));

    private ChallengerStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    public static ChallengerStatus getChallengerStatus(String value) {
        return CALLENCHALLENGER_STATUS_MAP.get(value);
    }
}
