package com.app.trycatch.common.enumeration.point;


import com.app.trycatch.common.enumeration.member.Gender;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum PointType {
    EARN("earn"), USE("use"), EXPIRE("expire"), PURCHASE_CANCEL("purchase_cancel");
    private String value;

    private static final Map<String, PointType> PointType_MAP =
            Arrays.stream(PointType.values()).collect(Collectors.toMap(PointType::getValue, Function.identity()));

    PointType(String value) {
        this.value = value;
    }

    public static PointType getPointType(String value) {
        return PointType_MAP.get(value);
    }

    public String getValue() {
        return value;
    }

}
