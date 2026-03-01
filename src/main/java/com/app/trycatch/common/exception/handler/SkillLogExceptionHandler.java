package com.app.trycatch.common.exception.handler;

import com.app.trycatch.common.exception.ExperienceProgramNotFoundException;
import com.app.trycatch.common.exception.FileNotFoundException;
import com.app.trycatch.common.exception.LoginFailException;
import com.app.trycatch.common.exception.SkillLogNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@ControllerAdvice(basePackages = "com.app.trycatch.controller.skilllog")
public class SkillLogExceptionHandler {
    @Autowired
    private HttpServletRequest request;

    @ExceptionHandler(SkillLogNotFoundException.class)
    protected RedirectView skillLogNotFound(SkillLogNotFoundException skillLogNotFoundException) {
        return new RedirectView("/skill-log/list");
    }
    @ExceptionHandler(ExperienceProgramNotFoundException.class)
    protected RedirectView ExperienceProgramNotFound(ExperienceProgramNotFoundException experienceProgramNotFoundException) {
        return new RedirectView("/skill-log/list");
    }

    @ExceptionHandler(FileNotFoundException.class)
    protected RedirectView fileNotFound(FileNotFoundException fileNotFoundException) {
        fileNotFoundException.printStackTrace();
        String url = request.getHeader("referer");
        return new RedirectView(url);
    }
}
