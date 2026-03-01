package com.app.trycatch.mapper;

import com.app.trycatch.domain.skilllog.SkillLogLikesVO;
import com.app.trycatch.dto.skilllog.SkillLogLikesDTO;
import com.app.trycatch.mapper.skilllog.SkillLogLikesMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class SkillLogLikesMapperTests {
    @Autowired
    private SkillLogLikesMapper skillLogLikeMapper;

    @Test
    public void testSelectCountBySkillLogId() {
        log.info("{}", skillLogLikeMapper.selectCountBySkillLogId(30L));
    }

    @Test
    public void testSelectLikedBySkillLogIdAndMemberId() {
        SkillLogLikesDTO skillLogLikeDTO = new SkillLogLikesDTO();
        skillLogLikeDTO.setSkillLogId(43L);
        skillLogLikeDTO.setMemberId(4L);
        log.info("{}", skillLogLikeMapper.selectBySkillLogIdAndMemberId(skillLogLikeDTO.toVO()).orElse(null));
    }

    @Test
    public void testInsert() {
        SkillLogLikesDTO skillLogLikeDTO = new SkillLogLikesDTO();
        skillLogLikeDTO.setSkillLogId(38L);
        skillLogLikeDTO.setMemberId(6L);

        skillLogLikeMapper.insert(skillLogLikeDTO.toVO());
    }

    @Test
    public void testDelete() {
        SkillLogLikesDTO skillLogLikeDTO = new SkillLogLikesDTO();
        skillLogLikeDTO.setSkillLogId(38L);
        skillLogLikeDTO.setMemberId(6L);

        SkillLogLikesVO skillLogLikeVO = skillLogLikeMapper.selectBySkillLogIdAndMemberId(skillLogLikeDTO.toVO()).orElse(null);

        skillLogLikeMapper.delete(skillLogLikeVO.getId());
    }

    @Test
    public void testDeleteBySkillLogId() {
        skillLogLikeMapper.deleteBySkillLogId(60L);
    }

}
