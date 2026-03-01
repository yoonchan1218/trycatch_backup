package com.app.trycatch.mapper;

import com.app.trycatch.common.enumeration.file.FileContentType;
import com.app.trycatch.common.pagination.Criteria;
import com.app.trycatch.domain.skilllog.SkillLogCommentVO;
import com.app.trycatch.dto.file.FileDTO;
import com.app.trycatch.dto.skilllog.SkillLogCommentDTO;
import com.app.trycatch.dto.skilllog.SkillLogCommentFileDTO;
import com.app.trycatch.mapper.file.FileMapper;
import com.app.trycatch.mapper.skilllog.SkillLogCommentMapper;
import com.app.trycatch.mapper.skilllog.SkillLogCommentFileMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class SkillLogCommentMapperTests {
    @Autowired
    private SkillLogCommentMapper skillLogCommentMapper;
    @Autowired
    private SkillLogCommentFileMapper skillLogCommentFileMapper;
    @Autowired
    private FileMapper fileMapper;

    @Test
    public void testInsert() {
        SkillLogCommentDTO skillLogCommentDTO = new SkillLogCommentDTO();
//        FileDTO fileDTO = new FileDTO();
        SkillLogCommentFileDTO skillLogCommentFileDTO = new SkillLogCommentFileDTO();

        skillLogCommentDTO.setMemberId(9L);
        skillLogCommentDTO.setSkillLogId(30L);
//        skillLogCommentDTO.setSkillLogCommentParentId(3L);
        skillLogCommentDTO.setSkillLogCommentContent("댓글3");

//        fileDTO.setFileName("테스트");
//        fileDTO.setFileSize("테스트");
//        fileDTO.setFilePath("테스트");
//        fileDTO.setFileOriginalName("테스트");
//        fileDTO.setFileContentType(FileContentType.IMAGE);

        skillLogCommentMapper.insert(skillLogCommentDTO);
//        fileMapper.insert(fileDTO);

//        skillLogCommentFileDTO.setSkillLogCommentId(skillLogCommentDTO.getId());
//        skillLogCommentFileDTO.setId(fileDTO.getId());

//        skillLogCommentFileMapper.insert(skillLogCommentFileDTO.toSkillLogCommentFileVO());

//        log.info("{}", skillLogCommentDTO.getId());
    }

    @Test
    public void testSelectAllBySkillLogId() {
        Criteria criteria = new Criteria(1, skillLogCommentMapper.selectCountAllBySkillLogId(31L));
        List<SkillLogCommentDTO> comments = skillLogCommentMapper.selectAllBySkillLogId(criteria, 31L);
        comments.forEach(comment -> log.info("{}", comment));
    }

    @Test
    public void testSelectAllByCommentParentIdAndSkillLogId() {
        Long skillLogId = 31L;
        Long commentParentId = 3L;
        Criteria criteria = new Criteria(1, skillLogCommentMapper.selectCountAllByCommentParentIdAndSkillLogId(skillLogId, commentParentId));
        List<SkillLogCommentDTO> replies = skillLogCommentMapper.selectAllByCommentParentIdAndSkillLogId(criteria, skillLogId, commentParentId);
        replies.forEach(reply -> log.info("{}", reply));
    }

    @Test
    public void testUpdate() {
        SkillLogCommentDTO skillLogCommentDTO = new SkillLogCommentDTO();
        skillLogCommentDTO.setId(1L);
        skillLogCommentDTO.setSkillLogCommentContent("수정된 댓글1");
        skillLogCommentMapper.update(skillLogCommentDTO.toVO());
    }

    @Test
    public void testDelete() {
        Long fileId = 44L;

        skillLogCommentFileMapper.delete(44L);
        fileMapper.delete(44L);
        skillLogCommentMapper.delete(6L);
    }

    @Test
    public void testDeleteAllByCommentParentIdAndSkillLogId() {
        Long skillLogId = 31L;
        Long commentParentId = 3L;

//        skillLogCommentFileMapper.deleteAllBySkillLogCommentParentIdAndSkillLogId(skillLogId, commentParentId);
//        skillLogCommentMapper.deleteAllBySkillLogCommentParentIdAndSkillLogId(skillLogId, commentParentId);
//        skillLogCommentMapper.delete(commentParentId);
    }

    @Test
    public void testDeleteAllBySkillLogId() {
        Long skillLogId = 30L;

        skillLogCommentFileMapper.deleteAllBySkillLogId(skillLogId);
//        skillLogCommentMapper.deleteAllBySkillLogId(skillLogId);
    }

}
