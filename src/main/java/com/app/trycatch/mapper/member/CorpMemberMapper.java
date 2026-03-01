package com.app.trycatch.mapper.member;

import com.app.trycatch.domain.member.CorpVO;
import com.app.trycatch.dto.member.CorpMemberDTO;
import com.app.trycatch.dto.member.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface CorpMemberMapper {
    void insert(CorpVO corpVO);

    //    회사명 검사
    Optional<CorpVO> selectByCorpCompanyName(String corpCompanyName);

    //    사업자등록번호 검사
    Optional<CorpVO> selectByCorpBusinessNumber(String corpBusinessNumber);

    //    기업회원 로그인 (tbl_member + tbl_corp JOIN, 비밀번호 검증)
    Optional<CorpMemberDTO> selectCorpMemberForLogin(MemberDTO memberDTO);

    //    기업회원 정보 조회 (tbl_member + tbl_corp + tbl_address JOIN)
    Optional<CorpMemberDTO> selectCorpMemberById(Long id);

    //    기업 정보 수정
    void updateCorp(CorpVO corpVO);
}
