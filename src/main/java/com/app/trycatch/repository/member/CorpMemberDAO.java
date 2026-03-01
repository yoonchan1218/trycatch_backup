package com.app.trycatch.repository.member;

import com.app.trycatch.domain.member.CorpVO;
import com.app.trycatch.dto.member.CorpMemberDTO;
import com.app.trycatch.dto.member.MemberDTO;
import com.app.trycatch.mapper.member.CorpMemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CorpMemberDAO {
    private final CorpMemberMapper corpMemberMapper;

    public void save(CorpVO corpVO) {
        corpMemberMapper.insert(corpVO);
    }

    //    기업명 검사
    public Optional<CorpVO> findByCorpCompanyName(String corpCompanyName) {
        return corpMemberMapper.selectByCorpCompanyName(corpCompanyName);
    }

    //    사업자등록번호 검사
    public Optional<CorpVO> findByCorpBusinessNumber(String corpBusinessNumber) {
        return corpMemberMapper.selectByCorpBusinessNumber(corpBusinessNumber);
    }

    //    기업회원 로그인
    public Optional<CorpMemberDTO> findForLogin(MemberDTO memberDTO) {
        return corpMemberMapper.selectCorpMemberForLogin(memberDTO);
    }

    //    기업 정보 조회 (tbl_member + tbl_corp + tbl_address JOIN)
    public Optional<CorpMemberDTO> findById(Long id) {
        return corpMemberMapper.selectCorpMemberById(id);
    }

    //    기업 정보 수정
    public void update(CorpVO corpVO) {
        corpMemberMapper.updateCorp(corpVO);
    }

}