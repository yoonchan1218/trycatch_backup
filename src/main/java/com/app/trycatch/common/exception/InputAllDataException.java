package com.app.trycatch.common.exception;

public class InputAllDataException extends RuntimeException {
    public InputAllDataException() {
        super("필수항목을 모두 입력하세요.");
    }

    public InputAllDataException(String message) {
        super(message);
    }
}
