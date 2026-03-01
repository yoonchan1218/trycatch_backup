package com.app.trycatch.common.enumeration.member;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Gender {
    MAN("man"), WOMEN("women");

    private String value;

    private static final Map<String, Gender> Gender_MAP =
            Arrays.stream(Gender.values()).collect(Collectors.toMap(Gender::getValue, Function.identity()));

    Gender(String value) {
        this.value = value;
    }

    public static Gender getGender(String value) {
        return Gender_MAP.get(value);
    }

    public String getValue() {
        return value;
    }

}
