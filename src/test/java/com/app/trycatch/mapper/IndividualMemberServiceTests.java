package com.app.trycatch.mapper;

import com.app.trycatch.common.enumeration.member.Gender;
import com.app.trycatch.common.enumeration.member.Provider;
import com.app.trycatch.domain.member.MemberVO;
import com.app.trycatch.dto.member.IndividualMemberDTO;
import com.app.trycatch.service.member.IndividualMemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class IndividualMemberServiceTests {
    @Autowired
    private IndividualMemberService individualMemberService;

    @Test
    public void testCheckMemberId() {
        log.info("{}...........................", individualMemberService.checkMemberId("sfasfasdfasdfasdf"));
        log.info("{}...........................", individualMemberService.checkMemberId("test"));
    }

    @Test
    public void testCheckEmail() {
        log.info("{}..........................", individualMemberService.checkEmail("testetst@gmail.com"));
        log.info("{}.......................", individualMemberService.checkEmail("test@gmail.com"));
    }

//    @Test
//    public void testJoinIndividual(){
//        IndividualMemberDTO individualMemberDTO = new IndividualMemberDTO();
//        individualMemberDTO.setMemberId("test12312");
//        individualMemberDTO.setMemberPassword("2333");
//        individualMemberDTO.setMemberName("젠슨 킴");
//        individualMemberDTO.setMemberEmail("test335@gmail.com");
//        individualMemberDTO.setMemberPhone("010-3334-2323");
//        individualMemberDTO.setMemberAgreePrivacy(true);
//        individualMemberDTO.setMemberAgreeMarketing(true);
//        individualMemberDTO.setProvider(Provider.TRYCATCH);
//        individualMemberDTO.setIndividualMemberBirth("1999-11-11");
//        individualMemberService.joinIndividual(individualMemberDTO);
//
//    }



}
