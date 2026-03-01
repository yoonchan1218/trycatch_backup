package com.app.trycatch.common.exception.handler;

import com.app.trycatch.common.exception.UnauthorizedMemberAccessException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.view.RedirectView;

@ControllerAdvice(basePackages = "com.app.trycatch.controller.point")
public class PointExceptionHandler {

    @ExceptionHandler(UnauthorizedMemberAccessException.class)
    protected RedirectView unauthorized(UnauthorizedMemberAccessException exception, HttpServletRequest request) {
        return new RedirectView("/main/log-in?re_url=" + request.getRequestURI());
    }
}
