package com.app.trycatch.interceptor;

import com.app.trycatch.dto.member.IndividualMemberDTO;
import com.app.trycatch.service.Alarm.IndividualAlramService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class IndividualAlramInterceptor implements HandlerInterceptor {
    private final IndividualAlramService individualAlramService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session == null || !(session.getAttribute("member") instanceof IndividualMemberDTO member)) {
            return true;
        }

        Long memberId = member.getId();
        request.setAttribute("qnaAlrams", individualAlramService.findQnaAlrams(memberId));
        request.setAttribute("applyAlrams", individualAlramService.findApplyAlrams(memberId));
        request.setAttribute("skillLogAlrams", individualAlramService.findSkillLogAlrams(memberId));
        return true;
    }
}
