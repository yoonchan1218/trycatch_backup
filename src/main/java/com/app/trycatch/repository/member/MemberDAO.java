package com.app.trycatch.repository.member;

import com.app.trycatch.domain.member.MemberVO;
import com.app.trycatch.domain.member.OAuthVO;
import com.app.trycatch.dto.member.MemberDTO;
import com.app.trycatch.mapper.member.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberDAO {
    private final MemberMapper memberMapper;

    //    아이디 검사
    public Optional<MemberVO> findByMemberId(String memberId) {
        return memberMapper.selectByMemberId(memberId);
    }
    //    이메일 검사
    public Optional<MemberVO> findByMemberEmail(String memberEmail) {
        return memberMapper.selectByMemberEmail(memberEmail);
    }

    // 개인회원 가입
    public void save(MemberVO memberVO) {
        memberMapper.insert(memberVO);
    }

    // 팀원 회원 가입 (DTO — useGeneratedKeys로 id 반환)
    public void saveTeamMember(MemberDTO memberDTO) {
        memberMapper.insertTeamMember(memberDTO);
    }

    // 기업회원 가입
    public void saveCorp(MemberVO memberVO) {
        memberMapper.insertCorp(memberVO);
    }

    // 소셜 로그인 정보 저장
    public void saveOauth(OAuthVO oAuthVO) {
        memberMapper.insertOauth(oAuthVO);
    }

    // 주소 id 추가
    public void updateAddressIdById(Long id) {
        memberMapper.updateAddressIdById(id);
    }

    // 회원 정보 수정
    public void update(MemberVO memberVO) {
        memberMapper.update(memberVO);
    }

    // 로그인
    public Optional<MemberVO> findForLogin(MemberDTO memberDTO) {
        return memberMapper.selectMemberForLogin(memberDTO);
    }
}