package com.app.trycatch.common.exception.handler;

import com.app.trycatch.common.exception.ExperienceProgramNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.view.RedirectView;

@ControllerAdvice(basePackages = "com.app.trycatch.controller.experience")
public class ExperienceExceptionHandler {

    @ExceptionHandler(ExperienceProgramNotFoundException.class)
    protected RedirectView experienceProgramNotFound(ExperienceProgramNotFoundException exception) {
        return new RedirectView("/experience/list");
    }
}
