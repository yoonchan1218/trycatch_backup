package com.app.trycatch.controller.experience;

import com.app.trycatch.domain.experience.ApplyVO;
import com.app.trycatch.dto.experience.ExperienceProgramDTO;
import com.app.trycatch.dto.member.IndividualMemberDTO;
import com.app.trycatch.mapper.experience.ExperienceProgramMapper;
import com.app.trycatch.repository.experience.ApplyDAO;
import com.app.trycatch.service.Alarm.CorporateAlramService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/apply")
@RequiredArgsConstructor
public class ApplyController {
    private final ApplyDAO applyDAO;
    private final ExperienceProgramMapper experienceProgramMapper;
    private final CorporateAlramService corporateAlramService;
    private final HttpSession session;

    @PostMapping
    public Map<String, Object> apply(@RequestParam Long experienceProgramId) {
        Object member = session.getAttribute("member");
        if (!(member instanceof IndividualMemberDTO individualMember)) {
            return Map.of("success", false, "message", "로그인이 필요합니다.");
        }

        Long memberId = individualMember.getId();
        if (applyDAO.existsByProgramIdAndMemberId(experienceProgramId, memberId)) {
            return Map.of("success", false, "message", "이미 지원한 프로그램입니다.");
        }

        try {
            ApplyVO applyVO = ApplyVO.builder()
                    .experienceProgramId(experienceProgramId)
                    .memberId(memberId)
                    .build();
            applyDAO.save(applyVO);
        } catch (DataIntegrityViolationException e) {
            return Map.of("success", false, "message", "이미 지원한 프로그램입니다.");
        }

        experienceProgramMapper.selectById(experienceProgramId).ifPresent(program -> {
            corporateAlramService.notify(
                    program.getCorpId(),
                    "experience_apply_received",
                    "새로운 지원자",
                    "[" + program.getExperienceProgramTitle() + "] 프로그램에 새로운 지원자가 있습니다."
            );
        });

        return Map.of("success", true);
    }
}
