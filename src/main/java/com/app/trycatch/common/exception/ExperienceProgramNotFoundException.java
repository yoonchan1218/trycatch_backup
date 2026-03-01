package com.app.trycatch.common.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ExperienceProgramNotFoundException extends RuntimeException {
    public ExperienceProgramNotFoundException(String message) {
        super(message);
    }
}
