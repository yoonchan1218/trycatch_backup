package com.app.trycatch.controller.skilllog;

import com.app.trycatch.common.search.Search;
import com.app.trycatch.dto.member.IndividualMemberDTO;
import com.app.trycatch.dto.member.MemberDTO;
import com.app.trycatch.dto.skilllog.ExperienceProgramWithPagingDTO;
import com.app.trycatch.dto.skilllog.SkillLogLikesDTO;
import com.app.trycatch.dto.skilllog.SkillLogWithPagingDTO;
import com.app.trycatch.service.skilllog.SkillLogService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/skill-log/**")
@RequiredArgsConstructor
@Slf4j
public class SkillLogAPIController {
    private final SkillLogService skillLogService;
    private final HttpSession session;

    @GetMapping("experience-program-logs/{page}")
    public ExperienceProgramWithPagingDTO experienceProgramLogs(@PathVariable int page, Search search) {
        Object member = session.getAttribute("member");
        Long memberId = null;

        if(member instanceof IndividualMemberDTO) {
            memberId = ((IndividualMemberDTO) member).getId();
        } else if (member instanceof MemberDTO) {
            memberId = ((MemberDTO) member).getId();
        }

        return skillLogService.recentExperienceLogs(memberId, page, search);
    }

    @GetMapping("list/{page}")
    public SkillLogWithPagingDTO list(@PathVariable int page, Search search) {
        return skillLogService.list(page, search);
    }

    @GetMapping("dashboard/{page}")
    public SkillLogWithPagingDTO myList(@PathVariable int page, Search search, Long memberId) {
        return skillLogService.myList(page, search, memberId);
    }

    @GetMapping("like")
    public int like(SkillLogLikesDTO skillLogLikesDTO) {
        return skillLogService.like(skillLogLikesDTO);
    }
}
