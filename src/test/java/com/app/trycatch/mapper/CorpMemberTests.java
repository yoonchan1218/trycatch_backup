package com.app.trycatch.mapper;

import com.app.trycatch.domain.member.AddressVO;
import com.app.trycatch.domain.member.CorpVO;
import com.app.trycatch.domain.member.MemberVO;
import com.app.trycatch.dto.member.CorpMemberDTO;
import com.app.trycatch.mapper.member.AddressMapper;
import com.app.trycatch.mapper.member.CorpMemberMapper;
import com.app.trycatch.mapper.member.MemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class CorpMemberTests {
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private CorpMemberMapper corpMemberMapper;

    @Test
    public void testInsert() {
//        CorpMemberDTO corpMemberDTO = new CorpMemberDTO();
//        corpMemberDTO.setMemberId("corp_test");
//        corpMemberDTO.setMemberPassword("1234");
//        corpMemberDTO.setMemberName("김대표");
//        corpMemberDTO.setMemberPhone("010-5678-5678");
//        corpMemberDTO.setMemberEmail("corp@gmail.com");
//        corpMemberDTO.setMemberAgreePrivacy(true);
//        corpMemberDTO.setMemberAgreeMarketing(false);
//
//        corpMemberDTO.setAddressZipcode("06100");
//        corpMemberDTO.setAddressAddress("서울특별시 강남구 역삼동");
//        corpMemberDTO.setAddressDetail("테헤란로 123");
//
//        corpMemberDTO.setCorpCompanyName("트라이캐치");
//        corpMemberDTO.setCorpBusinessNumber("123-45-67890");
//        corpMemberDTO.setCorpCeoName("김대표");
//        log.info("{}...........", corpMemberDTO);
//
//        AddressVO addressVO = corpMemberDTO.toAddressVO();
//        addressMapper.insert(addressVO);
//        log.info("{}................",addressVO);
//
//        corpMemberDTO.setAddressId(addressVO.getId());
//        MemberVO memberVO = corpMemberDTO.toMemberVO();
//        memberMapper.insertCorp(memberVO);
//        log.info("{}................",memberVO.getId());
//
//        corpMemberDTO.setId(memberVO.getId());
//
//        corpMemberMapper.insert(corpMemberDTO.toCorpVO());
    }

    // DTO → AddressVO 변환: addressId가 AddressVO.id로 매핑되는지
    @Test
    public void testToAddressVO_주소변환() {
        CorpMemberDTO dto = new CorpMemberDTO();
        dto.setAddressId(100L);
        dto.setAddressZipcode("06100");
        dto.setAddressAddress("서울특별시 강남구 역삼동");
        dto.setAddressDetail("테헤란로 123");

        AddressVO vo = dto.toAddressVO();

        log.info("AddressVO: {}", vo);

        assertEquals(100L, vo.getId(), "addressId가 AddressVO.id로 매핑되어야 합니다");
        assertEquals("06100", vo.getAddressZipcode());
        assertEquals("서울특별시 강남구 역삼동", vo.getAddressAddress());
        assertEquals("테헤란로 123", vo.getAddressDetail());
    }

    // id와 addressId가 다를 때 addressId가 사용되는지 확인
    @Test
    public void testToAddressVO_addressId_사용() {
        CorpMemberDTO dto = new CorpMemberDTO();
        dto.setId(1L);           // 회원 ID
        dto.setAddressId(999L);  // 주소 ID
        dto.setAddressZipcode("03101");
        dto.setAddressAddress("서울시 종로구");
        dto.setAddressDetail("세종로 456");

        AddressVO vo = dto.toAddressVO();

        assertEquals(999L, vo.getId(), "AddressVO.id는 addressId(999)여야 합니다, 회원 id(1)가 아닙니다");
    }

    // DTO → CorpVO 변환: corpMainBusiness 포함 전체 필드
    @Test
    public void testToCorpVO_기업정보변환() {
        CorpMemberDTO dto = new CorpMemberDTO();
        dto.setId(1L);
        dto.setCorpCompanyName("트라이캐치");
        dto.setCorpBusinessNumber("123-45-67890");
        dto.setCorpCeoName("김대표");
        dto.setCorpKindId(10L);
        dto.setCorpKindSmallId(101L);
        dto.setCorpCompanyType("주식회사");
        dto.setCorpCompanySize("중소기업");
        dto.setCorpEstablishmentDate("2020-01-01");
        dto.setCorpWebsiteUrl("https://trycatch.com");
        dto.setCorpFax("02-1234-5678");
        dto.setCorpCapital(100000000L);
        dto.setCorpTotalSales("5000000");
        dto.setCorpMainBusiness("소프트웨어 개발");
        dto.setCorpPerformance("연혁 및 실적 내용");
        dto.setCorpVision("비전 내용");

        CorpVO vo = dto.toCorpVO();

        log.info("CorpVO: {}", vo);

        assertEquals(1L, vo.getId());
        assertEquals("트라이캐치", vo.getCorpCompanyName());
        assertEquals(10L, vo.getCorpKindId());
        assertEquals(101L, vo.getCorpKindSmallId());
        assertEquals(100000000L, vo.getCorpCapital());
        assertEquals("소프트웨어 개발", vo.getCorpMainBusiness(), "corpMainBusiness가 정상 매핑되어야 합니다");
        assertEquals("연혁 및 실적 내용", vo.getCorpPerformance());
        assertEquals("비전 내용", vo.getCorpVision());
    }

    // corpMainBusiness와 corpPerformance가 독립적인지 확인
    @Test
    public void testToCorpVO_corpMainBusiness_독립저장() {
        CorpMemberDTO dto = new CorpMemberDTO();
        dto.setId(1L);
        dto.setCorpMainBusiness("주요사업: AI 개발");
        dto.setCorpPerformance("실적: 매출 100억 달성");

        CorpVO vo = dto.toCorpVO();

        assertNotEquals(vo.getCorpMainBusiness(), vo.getCorpPerformance(),
                "주요사업내용과 연혁및실적은 다른 필드여야 합니다");
        assertEquals("주요사업: AI 개발", vo.getCorpMainBusiness());
        assertEquals("실적: 매출 100억 달성", vo.getCorpPerformance());
    }

    // DTO → MemberVO 변환
    @Test
    public void testToMemberVO_회원정보변환() {
        CorpMemberDTO dto = new CorpMemberDTO();
        dto.setId(1L);
        dto.setMemberId("corp_test");
        dto.setMemberPassword("1234");
        dto.setMemberName("김대표");
        dto.setMemberPhone("010-5678-5678");
        dto.setMemberEmail("corp@gmail.com");
        dto.setAddressId(100L);
        dto.setMemberAgreePrivacy(true);
        dto.setMemberAgreeMarketing(false);

        MemberVO vo = dto.toMemberVO();

        log.info("MemberVO: {}", vo);

        assertEquals(1L, vo.getId());
        assertEquals("corp_test", vo.getMemberId());
        assertEquals("김대표", vo.getMemberName());
        assertEquals("010-5678-5678", vo.getMemberPhone());
        assertEquals(100L, vo.getAddressId());
        assertTrue(vo.isMemberAgreePrivacy());
        assertFalse(vo.isMemberAgreeMarketing());
    }

    // DB에서 기업회원 조회 시 addressAddress, corpMainBusiness 매핑 확인
    @Test
    public void testSelectCorpMemberById_조회() {
        corpMemberMapper.selectCorpMemberById(1L).ifPresent(dto -> {
            log.info("기업회원 조회: id={}, name={}, company={}", dto.getId(), dto.getMemberName(), dto.getCorpCompanyName());
            log.info("  주소(addressAddress): {}", dto.getAddressAddress());
            log.info("  주요사업내용(corpMainBusiness): {}", dto.getCorpMainBusiness());
            log.info("  업종소분류(corpKindSmallId): {}", dto.getCorpKindSmallId());
            log.info("  자본금(corpCapital): {}", dto.getCorpCapital());

            assertNotNull(dto.getId());
        });
    }
}
