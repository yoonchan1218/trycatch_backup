package com.app.trycatch.common.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UnauthorizedMemberAccessException extends RuntimeException {
    public UnauthorizedMemberAccessException(String message) {
        super(message);
    }
}
