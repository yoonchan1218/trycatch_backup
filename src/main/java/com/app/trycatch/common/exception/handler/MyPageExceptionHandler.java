package com.app.trycatch.common.exception.handler;

import com.app.trycatch.common.exception.InputAllDataException;
import com.app.trycatch.common.exception.MemberNotFoundException;
import com.app.trycatch.common.exception.UnauthorizedMemberAccessException;
import com.app.trycatch.common.exception.UnsubscribeNameMismatchException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@ControllerAdvice(basePackages = "com.app.trycatch.controller.mypage")
public class MyPageExceptionHandler {
    @ExceptionHandler(InputAllDataException.class)
    protected RedirectView inputAllData(InputAllDataException exception, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("input", "fail");
        return new RedirectView("/mypage/change-my-information");
    }

    @ExceptionHandler(MemberNotFoundException.class)
    protected RedirectView memberNotFound(MemberNotFoundException exception, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("mypage", "member-not-found");
        return new RedirectView("/main/log-in?re_url=" + request.getRequestURI());
    }

    @ExceptionHandler(UnauthorizedMemberAccessException.class)
    protected RedirectView unauthorized(UnauthorizedMemberAccessException exception, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("mypage", "unauthorized");
        return new RedirectView("/main/log-in?re_url=" + request.getRequestURI());
    }

    @ExceptionHandler(UnsubscribeNameMismatchException.class)
    protected RedirectView unsubscribeNameMismatch(UnsubscribeNameMismatchException exception, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("unsubscribe", "name-mismatch");
        return new RedirectView("/mypage/unsubscribe");
    }
}
