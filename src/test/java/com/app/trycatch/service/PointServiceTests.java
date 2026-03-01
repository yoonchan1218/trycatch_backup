package com.app.trycatch.service;

import com.app.trycatch.dto.point.PointDetailsDTO;
import com.app.trycatch.service.point.PointService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class PointServiceTests {
    @Autowired
    private PointService pointService;

    // 포인트 내역 목록 조회
    @Test
    public void testGetPointDetails() {
        List<PointDetailsDTO> list = pointService.getPointDetails(7L);
        list.forEach(dto -> log.info("{}......................", dto));
    }
}
