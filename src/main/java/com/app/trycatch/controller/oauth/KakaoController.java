package com.app.trycatch.controller.oauth;

import com.app.trycatch.dto.member.IndividualMemberDTO;
import com.app.trycatch.service.member.IndividualMemberService;
import com.app.trycatch.service.oauth.KakaoService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Slf4j
@RequiredArgsConstructor
public class KakaoController {
    private final KakaoService kakaoService;
    private final IndividualMemberService individualMemberService;
    private final HttpSession session;

    @GetMapping("/kakao/login")
    public RedirectView kakaoLogin(String code, RedirectAttributes redirectAttributes) {
        IndividualMemberDTO memberDTO = kakaoService.kakaoLogin(code);
        String path = null;

        if (memberDTO.getId() == null) {
            redirectAttributes.addFlashAttribute("kakao", memberDTO);
            path = "/member/kakao/join";
        } else {
            session.setAttribute("member", memberDTO);
            path = "/qna/list";
        }

        return new RedirectView(path);
    }
}
