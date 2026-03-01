package com.app.trycatch.common.enumeration.qna;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum QnaStatus {
    PUBLISHED("published"), DELETED("deleted");

    private String value;

    private static final Map<String, QnaStatus> STATUS_MAP =
            Arrays.stream(QnaStatus.values()).collect(Collectors.toMap(QnaStatus::getValue, Function.identity()));

    QnaStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
