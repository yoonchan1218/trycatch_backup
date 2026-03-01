package com.app.trycatch.controller.alarm;

import com.app.trycatch.dto.member.IndividualMemberDTO;
import com.app.trycatch.service.Alarm.IndividualAlramService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/alarm/**")
@RequiredArgsConstructor
public class IndividualAlramController {
    private final IndividualAlramService individualAlramService;
    private final HttpSession session;

    @PutMapping("read")
    public void readAll() {
        Object member = session.getAttribute("member");
        if (member instanceof IndividualMemberDTO individualMember) {
            individualAlramService.readAll(individualMember.getId());
        }
    }
}
