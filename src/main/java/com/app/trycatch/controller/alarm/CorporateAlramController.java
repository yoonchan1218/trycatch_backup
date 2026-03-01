package com.app.trycatch.controller.alarm;

import com.app.trycatch.dto.member.CorpMemberDTO;
import com.app.trycatch.service.Alarm.CorporateAlramService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/corp-alarm")
@RequiredArgsConstructor
public class CorporateAlramController {
    private final CorporateAlramService corporateAlramService;
    private final HttpSession session;

    @PutMapping("/read")
    public void readAll() {
        Object member = session.getAttribute("member");
        if (member instanceof CorpMemberDTO corpMember) {
            corporateAlramService.readAll(corpMember.getId());
        }
    }
}
