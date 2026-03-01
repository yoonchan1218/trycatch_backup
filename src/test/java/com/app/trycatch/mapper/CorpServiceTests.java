package com.app.trycatch.mapper;

import com.app.trycatch.dto.corporate.CorpProgramWithPagingDTO;
import com.app.trycatch.dto.experience.ExperienceProgramDTO;
import com.app.trycatch.dto.member.CorpMemberDTO;
import com.app.trycatch.service.corporate.CorporateService;
import com.app.trycatch.service.member.CorpService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
public class CorpServiceTests {
//    @Autowired
//    private CorpService corpService;

    @Autowired
    private CorporateService corporateService;

    private static final Long TEST_CORP_ID = 1L;

    // ── getProgramStats 테스트 ─────────────────────────────────

    @Test
    public void testGetProgramStats_상태별_프로그램_수() {
        Map<String, Long> stats = corporateService.getProgramStats(TEST_CORP_ID);

        log.info("상태별 프로그램 수: {}", stats);

        assertNotNull(stats);
        assertTrue(stats.containsKey("draft"));
        assertTrue(stats.containsKey("recruiting"));
        assertTrue(stats.containsKey("closed"));
        assertTrue(stats.containsKey("cancelled"));
    }

    // ── getPrograms 테스트 ──────────────────────────────────────

    @Test
    public void testGetPrograms_전체목록() {
        CorpProgramWithPagingDTO result = corporateService.getPrograms(TEST_CORP_ID, 1, 10, "", "", "1");

        log.info("전체 목록: list.size={}, hasMore={}", result.getList().size(), result.isHasMore());
        result.getList().forEach(p -> log.info("  id={}, title={}, status={}",
                p.getId(), p.getExperienceProgramTitle(), p.getExperienceProgramStatus()));

        assertNotNull(result);
        assertNotNull(result.getList());
        assertNotNull(result.getCriteria());
        assertTrue(result.getList().size() <= 10, "목록 수는 TopCount(10) 이하여야 합니다");
    }

    @Test
    public void testGetPrograms_상태필터_recruiting() {
        CorpProgramWithPagingDTO result = corporateService.getPrograms(TEST_CORP_ID, 1, 10, "recruiting", "", "1");

        log.info("모집중 목록: list.size={}", result.getList().size());

        result.getList().forEach(p -> {
            assertEquals("RECRUITING", p.getExperienceProgramStatus().name(),
                    "모집중 필터 시 상태가 RECRUITING이어야 합니다");
        });
    }

    @Test
    public void testGetPrograms_키워드검색() {
        CorpProgramWithPagingDTO result = corporateService.getPrograms(TEST_CORP_ID, 1, 10, "", "테스트", "1");

        log.info("키워드 '테스트' 검색: list.size={}", result.getList().size());

        assertNotNull(result.getList());
    }

    @Test
    public void testGetPrograms_페이징() {
        CorpProgramWithPagingDTO result = corporateService.getPrograms(TEST_CORP_ID, 1, 5, "", "", "1");

        log.info("페이징 테스트: page={}, startPage={}, endPage={}, realEnd={}, hasMore={}",
                result.getCriteria().getPage(),
                result.getCriteria().getStartPage(),
                result.getCriteria().getEndPage(),
                result.getCriteria().getRealEnd(),
                result.isHasMore());

        assertEquals(1, result.getCriteria().getPage());
        assertTrue(result.getList().size() <= 5);
    }

    // ── getRecentPrograms 테스트 ────────────────────────────────

    @Test
    public void testGetRecentPrograms_최신프로그램() {
        List<ExperienceProgramDTO> list = corporateService.getRecentPrograms(TEST_CORP_ID, 5);

        log.info("최신 프로그램 5개: size={}", list.size());
        list.forEach(p -> log.info("  id={}, title={}", p.getId(), p.getExperienceProgramTitle()));

        assertNotNull(list);
        assertTrue(list.size() <= 5);
    }

    // ── getCorpInfo 테스트 ──────────────────────────────────────

    @Test
    public void testGetCorpInfo_기업정보조회() {
        CorpMemberDTO info = corporateService.getCorpInfo(TEST_CORP_ID);

        log.info("기업정보: id={}, company={}, ceo={}", info.getId(), info.getCorpCompanyName(), info.getCorpCeoName());
        log.info("  주소: {}", info.getAddressAddress());
        log.info("  주요사업: {}", info.getCorpMainBusiness());
        log.info("  자본금: {}", info.getCorpCapital());
        log.info("  업종소분류: {}", info.getCorpKindSmallId());

        assertNotNull(info);
        assertEquals(TEST_CORP_ID, info.getId());
    }

    // ── isCorpMember 테스트 ────────────────────────────────────

    @Test
    public void testIsCorpMember_존재하는_기업회원() {
        boolean result = corporateService.isCorpMember(TEST_CORP_ID);
        log.info("기업회원 여부 (id={}): {}", TEST_CORP_ID, result);
        assertTrue(result);
    }

    @Test
    public void testIsCorpMember_존재하지_않는_회원() {
        boolean result = corporateService.isCorpMember(99999L);
        log.info("기업회원 여부 (id=99999): {}", result);
        assertFalse(result);
    }
}
