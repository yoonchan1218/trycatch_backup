package com.app.trycatch.interceptor;

import com.app.trycatch.dto.member.CorpMemberDTO;
import com.app.trycatch.service.Alarm.CorporateAlramService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class CorporateAlarmInterceptor implements HandlerInterceptor {
    private final CorporateAlramService corporateAlramService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session == null || !(session.getAttribute("member") instanceof CorpMemberDTO member)) {
            return true;
        }

        Long corpId = member.getId();
        request.setAttribute("corpAlrams", corporateAlramService.list(corpId));
        request.setAttribute("hasUnreadCorpAlrams", corporateAlramService.hasUnread(corpId));
        return true;
    }
}
