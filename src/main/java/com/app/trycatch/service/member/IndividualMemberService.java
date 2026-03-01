package com.app.trycatch.service.member;

import com.app.trycatch.common.enumeration.member.Provider;
import com.app.trycatch.common.exception.LoginFailException;
import com.app.trycatch.domain.member.MemberVO;
import com.app.trycatch.dto.member.IndividualMemberDTO;
import com.app.trycatch.dto.member.MemberDTO;
import com.app.trycatch.repository.member.IndividualMemberDAO;
import com.app.trycatch.repository.member.MemberDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class IndividualMemberService {
    private final MemberDAO memberDAO;
    private final IndividualMemberDAO individualMemberDAO;


    // 아이디 중복 검사 (true: 사용 가능)
    public boolean checkMemberId(String memberId) {
        return memberDAO.findByMemberId(memberId).isEmpty();
    }

    // 이메일 중복 검사 (true: 사용 가능)
    public boolean checkEmail(String memberEmail) {
        return memberDAO.findByMemberEmail(memberEmail).isEmpty();
    }


    //   개인 회원가입
    public void joinIndividual(IndividualMemberDTO individualMemberDTO) {
        MemberVO memberVO = individualMemberDTO.toMemberVO();
        memberDAO.save(memberVO);

        individualMemberDTO.setId(memberVO.getId());
        individualMemberDTO.setProvider(Provider.TRYCATCH);

        memberDAO.saveOauth(individualMemberDTO.toOAuthVO());
        individualMemberDAO.save(individualMemberDTO.toIndividualMemberVO());
    }

    //        카카오 개인 회원가입
    public void kakaoJoin(IndividualMemberDTO individualMemberDTO) {
        if (individualMemberDTO.getMemberId() == null || individualMemberDTO.getMemberId().isEmpty()) {
            individualMemberDTO.setMemberId(UUID.randomUUID().toString().substring(0, 16));
        }
        if (individualMemberDTO.getMemberPassword() == null || individualMemberDTO.getMemberPassword().isEmpty()) {
            individualMemberDTO.setMemberPassword(UUID.randomUUID().toString());
        }

        MemberVO memberVO = individualMemberDTO.toMemberVO();
        memberDAO.save(memberVO);

        individualMemberDTO.setId(memberVO.getId());
        individualMemberDTO.setProvider(Provider.KAKAO);

        memberDAO.saveOauth(individualMemberDTO.toOAuthVO());
        individualMemberDAO.save(individualMemberDTO.toIndividualMemberVO());
    }

    //    로그인
    public IndividualMemberDTO login(MemberDTO memberDTO) {
        MemberVO memberVO = memberDAO.findForLogin(memberDTO).orElseThrow(LoginFailException::new);
        return individualMemberDAO.findById(memberVO.getId()).orElseThrow(LoginFailException::new);
    }

    //    ID로 회원 조회 (카카오 로그인 등에서 사용)
    public IndividualMemberDTO findById(Long id) {
        return individualMemberDAO.findById(id).orElseThrow(LoginFailException::new);
    }
}
