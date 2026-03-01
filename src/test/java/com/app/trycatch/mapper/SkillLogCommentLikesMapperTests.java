package com.app.trycatch.mapper;

import com.app.trycatch.domain.skilllog.SkillLogCommentLikesVO;
import com.app.trycatch.dto.skilllog.SkillLogCommentLikesDTO;
import com.app.trycatch.mapper.skilllog.SkillLogCommentLikesMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class SkillLogCommentLikesMapperTests {
    @Autowired
    private SkillLogCommentLikesMapper skillLogCommentLikesMapper;

    @Test
    public void testSelectCountBySkillLogCommentId() {
        log.info("{}", skillLogCommentLikesMapper.selectCountBySkillLogCommentId(75L));
    }

    @Test
    public void testSelectLikedBySkillLogCommentIdAndMemberId() {
        SkillLogCommentLikesDTO skillLogCommentLikesDTO = new SkillLogCommentLikesDTO();
        skillLogCommentLikesDTO.setSkillLogCommentId(75L);
        skillLogCommentLikesDTO.setMemberId(6L);

        log.info("{}",
                skillLogCommentLikesMapper.selectBySkillLogCommentIdAndMemberId(skillLogCommentLikesDTO.toVO()).orElse(null));
    }

    @Test
    public void testInsert() {
        SkillLogCommentLikesDTO skillLogCommentLikesDTO = new SkillLogCommentLikesDTO();
        skillLogCommentLikesDTO.setSkillLogCommentId(75L);
        skillLogCommentLikesDTO.setMemberId(6L);

        skillLogCommentLikesMapper.insert(skillLogCommentLikesDTO.toVO());
    }

    @Test
    public void testDelete() {
        SkillLogCommentLikesDTO skillLogCommentLikesDTO = new SkillLogCommentLikesDTO();
        skillLogCommentLikesDTO.setSkillLogCommentId(75L);
        skillLogCommentLikesDTO.setMemberId(6L);

        SkillLogCommentLikesVO skillLogCommentLikesVO = skillLogCommentLikesMapper.selectBySkillLogCommentIdAndMemberId(skillLogCommentLikesDTO.toVO()).orElse(null);

        skillLogCommentLikesMapper.delete(skillLogCommentLikesVO.getId());
    }

    @Test
    public void testDeleteBySkillLogCommentId() {
        skillLogCommentLikesMapper.deleteBySkillLogCommentId(84L);
    }

    @Test
    public void testDeleteBySkillLogId() {
        skillLogCommentLikesMapper.deleteBySkillLogId(480L);
    }
}
