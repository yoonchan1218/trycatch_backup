package com.app.trycatch.service;

import com.app.trycatch.service.tag.TagService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class TagServiceTests {
    @Autowired
    private TagService tagService;

    @Test
    public void testSelectAll() {
        log.info("{}", tagService.selectAll());
    }
}
