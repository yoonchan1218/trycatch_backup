package com.app.trycatch.mapper;

import com.app.trycatch.common.enumeration.file.FileContentType;
import com.app.trycatch.domain.skilllog.SkillLogFileVO;
import com.app.trycatch.dto.file.FileDTO;
import com.app.trycatch.dto.skilllog.SkillLogFileDTO;
import com.app.trycatch.mapper.file.FileMapper;
import com.app.trycatch.mapper.skilllog.SkillLogFileMapper;
import com.app.trycatch.repository.file.FileDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class SkillLogFileMapperTests {
    @Autowired
    private SkillLogFileMapper skillLogFileMapper;
    @Autowired
    private FileMapper fileMapper;

    @Test
    public void testInert() {
        FileDTO fileDTO = new FileDTO();
        SkillLogFileDTO skillLogFileDTO = new SkillLogFileDTO();

        fileDTO.setFileName("테스트");
        fileDTO.setFilePath("테스트");
        fileDTO.setFileOriginalName("테스트");
        fileDTO.setFileSize("테스트");
        fileDTO.setFileContentType(FileContentType.IMAGE);

        fileMapper.insert(fileDTO);

        skillLogFileDTO.setId(fileDTO.getId());
        skillLogFileDTO.setSkillLogId(13L);

        skillLogFileMapper.insert(skillLogFileDTO.toSkillLogFileVO());
    }

    @Test
    public void testSelectAllBySkillLogId() {
        List<SkillLogFileDTO> skillLogFiles = skillLogFileMapper.selectAllBySkillLogId(31L);
        skillLogFiles.forEach((skillLogFileDTO) -> log.info("{}", skillLogFileDTO));
    }

    @Test
    public void testDelete() {
        Long id = 16L;

        skillLogFileMapper.delete(id);
        fileMapper.delete(id);
    }
}
