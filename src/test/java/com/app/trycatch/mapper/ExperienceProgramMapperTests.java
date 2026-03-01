package com.app.trycatch.mapper;

import com.app.trycatch.common.pagination.Criteria;
import com.app.trycatch.common.search.Search;
import com.app.trycatch.dto.experience.ExperienceProgramDTO;
import com.app.trycatch.mapper.experience.ExperienceProgramMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class ExperienceProgramMapperTests {
    @Autowired
    private ExperienceProgramMapper experienceProgramMapper;

    @Test
    public void testSelectAllByChallengerMemberId() {
        Search search = new Search();
        Long memberId = 11L;

        search.setKeyword("");

        int total = experienceProgramMapper.selectTotalByMemberIdOfChallenger(search, memberId);
        Criteria criteria = new Criteria(1, total);

        List<ExperienceProgramDTO> experiencePrograms =
                experienceProgramMapper.selectAllByMemberIdOfChallenger(criteria, search, memberId);

        experiencePrograms.forEach((experienceProgram) -> log.info("{}", experienceProgram));
    }

    @Test
    public void testSelectById() {
        log.info("{}", experienceProgramMapper.selectById(1L).orElse(null));
    }

    @Test
    public void testCountByStatus_상태별_프로그램_수() {
        Long corpId = 1L;
        Map<String, Long> stats = experienceProgramMapper.countByStatus(corpId);

        log.info("상태별 프로그램 수: {}", stats);

        assertNotNull(stats, "결과 Map이 null이면 안됩니다");
        assertTrue(stats.containsKey("draft"), "draft 키가 있어야 합니다");
        assertTrue(stats.containsKey("recruiting"), "recruiting 키가 있어야 합니다");
        assertTrue(stats.containsKey("closed"), "closed 키가 있어야 합니다");
        assertTrue(stats.containsKey("cancelled"), "cancelled 키가 있어야 합니다");

        stats.forEach((key, value) -> {
            assertNotNull(value, key + " 값이 null이면 안됩니다");
            assertTrue(value >= 0, key + " 값은 0 이상이어야 합니다");
        });
    }

    @Test
    public void testCountByCorpId_전체_개수() {
        Long corpId = 1L;
        int total = experienceProgramMapper.countByCorpId(corpId, "", "");

        log.info("기업 ID {} 전체 프로그램 수: {}", corpId, total);
        assertTrue(total >= 0, "프로그램 수는 0 이상이어야 합니다");
    }

    @Test
    public void testCountByCorpId_상태필터() {
        Long corpId = 1L;
        int recruiting = experienceProgramMapper.countByCorpId(corpId, "recruiting", "");
        int closed = experienceProgramMapper.countByCorpId(corpId, "closed", "");

        log.info("recruiting={}, closed={}", recruiting, closed);
        assertTrue(recruiting >= 0);
        assertTrue(closed >= 0);
    }

    @Test
    public void testCountByCorpId_키워드검색() {
        Long corpId = 1L;
        int total = experienceProgramMapper.countByCorpId(corpId, "", "테스트");

        log.info("키워드 '테스트' 검색 결과 수: {}", total);
        assertTrue(total >= 0);
    }

    @Test
    public void testSelectByCorpId_목록조회() {
        Long corpId = 1L;
        int total = experienceProgramMapper.countByCorpId(corpId, "", "");
        Criteria criteria = new Criteria(1, total);

        List<ExperienceProgramDTO> list =
                experienceProgramMapper.selectByCorpId(corpId, criteria, "", "", "1");

        log.info("프로그램 목록 수: {}", list.size());
        list.forEach(p -> log.info("  id={}, title={}, status={}",
                p.getId(), p.getExperienceProgramTitle(), p.getExperienceProgramStatus()));

        assertNotNull(list);
    }

    @Test
    public void testSelectByCorpId_상태필터_recruiting() {
        Long corpId = 1L;
        int total = experienceProgramMapper.countByCorpId(corpId, "recruiting", "");
        Criteria criteria = new Criteria(1, total);

        List<ExperienceProgramDTO> list =
                experienceProgramMapper.selectByCorpId(corpId, criteria, "recruiting", "", "1");

        log.info("모집중 프로그램 수: {}", list.size());
        list.forEach(p -> {
            log.info("  id={}, title={}, status={}", p.getId(), p.getExperienceProgramTitle(), p.getExperienceProgramStatus());
            assertEquals("RECRUITING", p.getExperienceProgramStatus().name(),
                    "필터링된 프로그램 상태는 RECRUITING이어야 합니다");
        });
    }

    @Test
    public void testSelectLatestByCorpId_최신프로그램() {
        Long corpId = 1L;
        int limit = 5;

        List<ExperienceProgramDTO> list = experienceProgramMapper.selectLatestByCorpId(corpId, limit);

        log.info("최신 프로그램 {}개: {}", limit, list.size());
        list.forEach(p -> log.info("  id={}, title={}", p.getId(), p.getExperienceProgramTitle()));

        assertNotNull(list);
        assertTrue(list.size() <= limit, "결과 수는 limit 이하여야 합니다");
    }
}
