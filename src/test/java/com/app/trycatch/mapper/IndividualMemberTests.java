package com.app.trycatch.mapper;

import com.app.trycatch.common.enumeration.member.Gender;
import com.app.trycatch.common.enumeration.member.Provider;
import com.app.trycatch.domain.member.MemberVO;
import com.app.trycatch.dto.member.IndividualMemberDTO;
import com.app.trycatch.dto.member.MemberDTO;
import com.app.trycatch.mapper.member.IndividualMemberMapper;
import com.app.trycatch.mapper.member.MemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class IndividualMemberTests {
//    @Autowired
//    private MemberMapper memberMapper;
//    @Autowired
//    private IndividualMemberMapper individualMemberMapper;
//
//    @Test
//    public void testInsert(){
//        IndividualMemberDTO individualMemberDTO = new IndividualMemberDTO();
//        log.info("{}............",individualMemberDTO);
//        individualMemberDTO.setMemberId("test123");
//        individualMemberDTO.setMemberPassword("2222");
//        individualMemberDTO.setMemberGender(Gender.MAN);
//        individualMemberDTO.setMemberName("스티브 잡스");
//        individualMemberDTO.setMemberEmail("test12345@gmail.com");
//        individualMemberDTO.setMemberPhone("010-2224-1222");
//        individualMemberDTO.setMemberAgreePrivacy(true);
//        individualMemberDTO.setMemberAgreeMarketing(true);
//        individualMemberDTO.setProvider(Provider.TRYCATCH);
//
//        MemberVO memberVO = individualMemberDTO.toMemberVO();
//        memberMapper.insert(memberVO);
//
//        individualMemberDTO.setId(memberVO.getId());
//        individualMemberDTO.setIndividualMemberBirth("1999-11-11");
//
//        individualMemberMapper.insert(individualMemberDTO.toIndividualMemberVO());
//
//        memberMapper.insertOauth(individualMemberDTO.toOAuthVO());
//    }



}
