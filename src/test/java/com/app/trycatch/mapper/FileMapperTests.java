package com.app.trycatch.mapper;

import com.app.trycatch.domain.file.FileVO;
import com.app.trycatch.mapper.file.FileMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class FileMapperTests {
    @Autowired
    private FileMapper fileMapper;

    @Test
    public void testSelectById() {
        FileVO fileVO = fileMapper.selectById(34L).orElse(null);
        log.info("{}", fileVO);
    }
}
