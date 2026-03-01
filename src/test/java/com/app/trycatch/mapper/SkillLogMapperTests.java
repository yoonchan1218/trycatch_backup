package com.app.trycatch.mapper;

import com.app.trycatch.common.enumeration.file.FileContentType;
import com.app.trycatch.common.pagination.Criteria;
import com.app.trycatch.common.search.Search;
import com.app.trycatch.domain.skilllog.SkillLogVO;
import com.app.trycatch.dto.skilllog.SkillLogAsideDTO;
import com.app.trycatch.dto.skilllog.SkillLogDTO;
import com.app.trycatch.dto.skilllog.SkillLogDashboardDTO;
import com.app.trycatch.dto.skilllog.TagDTO;
import com.app.trycatch.mapper.skilllog.SkillLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
public class SkillLogMapperTests {
    @Autowired
    private SkillLogMapper skillLogMapper;

    @Test
    public void testInsert() {
        SkillLogDTO skillLogDTO = new SkillLogDTO();

        skillLogDTO.setMemberId(1L);
        skillLogDTO.setExperienceProgramId(2L);
        skillLogDTO.setSkillLogTitle("기술 블로그 테스트1");
        skillLogDTO.setSkillLogContent("내용1");

        skillLogMapper.insert(skillLogDTO);

        log.info("{}................", skillLogDTO.getId());
    }

    @Test
    public void testSelectCountByMemberId() {
        SkillLogAsideDTO skillLogAsideDTO = skillLogMapper.selectProfileByMemberId(9L);
        log.info("{}.....................", skillLogAsideDTO);
    }

    @Test
    public void testSelectAll() {
        Search search = new Search();
        Criteria criteria = null;
        List<SkillLogDTO> skillLogs = null;
        String[] tagNames = new String[1];

//        search.setKeyword("1");
//        tagNames[0] = "태그";
//        search.setTagNames(tagNames);
        search.setType("인기");

        criteria = new Criteria(1, skillLogMapper.selectTotal(search));
        skillLogs = skillLogMapper.selectAll(criteria, search);

        skillLogs.forEach((skillLog) -> {
            log.info("{}", skillLog);
        });
    }

    @Test
    public void testSelectById() {
        log.info("{}", skillLogMapper.selectById(66L).orElse(null));
    }

    @Test
    public void testUpdateSkillLogViewCount() {
        skillLogMapper.updateSkillLogViewCount(30L);
    }

    @Test
    public void testUpdate() {
        SkillLogDTO skillLogDTO = new SkillLogDTO();

        skillLogDTO.setId(30L);
        skillLogDTO.setSkillLogTitle("update test");
        skillLogDTO.setSkillLogContent("update");
        skillLogDTO.setExperienceProgramId(5L);

        skillLogMapper.update(skillLogDTO.toSkillLogVO());
    }

    @Test
    public void testUpdateSkillLogStatus() {
        skillLogMapper.updateSkillLogStatus(481L);
    }

    @Test
    public void testSelectAllByMemberId() {
        Search search = new Search();
        Criteria criteria = null;
        List<SkillLogDTO> skillLogs = null;
        String[] tagNames = new String[1];
        Long memberId = 9L;

        search.setTagNames(tagNames);
        search.setType("최신순");

        criteria = new Criteria(1, skillLogMapper.selectTotalByMemberId(memberId));
        skillLogs = skillLogMapper.selectAllByMemberId(criteria, search, memberId);


        for (int i = 0; i < skillLogs.size(); i++)  {
            log.info("{}", i) ;
            log.info("{}", skillLogs.get(i));
        }
    }

    @Test
    public void testSelectDashboardByMemberId() {
        SkillLogDashboardDTO skillLogDashboardDTO = skillLogMapper.selectDashboardByMemberId(12L);
        log.info("{}", skillLogDashboardDTO);
    }
}
