package com.app.trycatch.controller.mail;

import com.app.trycatch.domain.corporate.CorpInviteVO;
import com.app.trycatch.dto.member.CorpMemberDTO;
import com.app.trycatch.dto.member.MemberDTO;
import com.app.trycatch.repository.corporate.CorpInviteDAO;
import com.app.trycatch.repository.member.CorpMemberDAO;
import com.app.trycatch.service.corporate.CorporateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

    private final CorporateService corporateService;
    private final CorpInviteDAO corpInviteDAO;
    private final CorpMemberDAO corpMemberDAO;

    // 가입 폼 표시
    @GetMapping("/invite/join")
    public String joinForm(@RequestParam String code, @RequestParam(required = false) String error, Model model) {
        Optional<CorpInviteVO> inviteOpt = corpInviteDAO.findByInviteCode(code);
        if (inviteOpt.isEmpty()) {
            return "redirect:/mail/invite/fail";
        }

        CorpInviteVO invite = inviteOpt.get();
        String corpName = corpMemberDAO.findById(invite.getCorpId())
                .map(CorpMemberDTO::getCorpCompanyName)
                .orElse("TRY-CATCH 기업");

        model.addAttribute("inviteCode", code);
        model.addAttribute("email", invite.getInviteEmail());
        model.addAttribute("corpName", corpName);
        model.addAttribute("error", error);
        return "mail/team-member-join";
    }

    // 가입 처리
    @PostMapping("/invite/join")
    public String joinProcess(@RequestParam String inviteCode,
                              @RequestParam String memberId,
                              @RequestParam String memberPassword,
                              @RequestParam String memberName,
                              @RequestParam String memberPhone,
                              @RequestParam String memberEmail) {
        try {
            MemberDTO memberDTO = new MemberDTO();
            memberDTO.setMemberId(memberId);
            memberDTO.setMemberPassword(memberPassword);
            memberDTO.setMemberName(memberName);
            memberDTO.setMemberPhone(memberPhone);
            memberDTO.setMemberEmail(memberEmail);

            corporateService.joinTeamMember(inviteCode, memberDTO);
            return "redirect:/mail/invite/success";
        } catch (IllegalArgumentException e) {
            log.warn("팀원 가입 실패: code={}, reason={}", inviteCode, e.getMessage());
            return "redirect:/mail/invite/fail";
        } catch (org.springframework.dao.DuplicateKeyException e) {
            log.warn("팀원 가입 실패 - 중복: code={}, reason={}", inviteCode, e.getMessage());
            return "redirect:/mail/invite/join?code=" + inviteCode + "&error=duplicate";
        }
    }

    @GetMapping("/invite/success")
    public String inviteSuccess() {
        return "mail/invite-success";
    }

    @GetMapping("/invite/fail")
    public String inviteFail() {
        return "mail/invite-fail";
    }
}
