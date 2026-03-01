package com.app.trycatch.mapper;

import com.app.trycatch.common.enumeration.member.Gender;
import com.app.trycatch.domain.member.IndividualMemberVO;
import com.app.trycatch.domain.member.MemberVO;
import com.app.trycatch.dto.mypage.MyPageProfileDTO;
import com.app.trycatch.mapper.mypage.MyPageMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MyPageMapperTests {
//    @Autowired
//    private MyPageMapper myPageMapper;
//
//    @Test
//    public void testSelectProfileByMemberId() {
//        MyPageProfileDTO profile = myPageMapper.selectProfileByMemberId(1L).orElse(null);
//        log.info("{}......................", profile);
//    }
//
//    @Test
//    public void testUpdateMember() {
//        MemberVO memberVO = MemberVO.builder()
//                .id(1L)
//                .memberName("홍길동")
//                .memberEmail("test@test.com")
//                .memberPhone("010-1234-5678")
//                .build();
//
//        myPageMapper.updateMember(memberVO);
//        log.info("회원정보 수정 완료 - {}", memberVO);
//    }
//
//    @Test
//    public void testUpdateIndividualMember() {
//        IndividualMemberVO individualMemberVO = IndividualMemberVO.builder()
//                .id(1L)
//                .individualMemberBirth("1990-01-01")
//                .individualMemberGender(Gender.MAN)
//                .individualMemberEducation("대졸")
//                .individualMemberZipcode("12345")
//                .individualMemberAddress("서울시 강남구")
//                .individualMemberAddressDetail("101호")
//                .build();
//
//        myPageMapper.updateIndividualMember(individualMemberVO);
//        log.info("개인회원 정보 수정 완료 - {}", individualMemberVO);
//    }
//
//    @Test
//    public void testUpdateMemberStatusToInactive() {
//        myPageMapper.updateMemberStatusToInactive(1L);
//        log.info("회원탈퇴 처리 완료 - memberId: 1");
//    }
}
