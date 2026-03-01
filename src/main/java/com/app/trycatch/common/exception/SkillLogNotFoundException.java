package com.app.trycatch.common.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SkillLogNotFoundException extends RuntimeException {
    public SkillLogNotFoundException(String message) {
        super(message);
    }
}
