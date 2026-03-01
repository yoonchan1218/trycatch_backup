package com.app.trycatch.mapper;

import com.app.trycatch.common.enumeration.point.PointType;
import com.app.trycatch.domain.point.PointDetailsVO;
import com.app.trycatch.dto.point.PointDetailsDTO;
import com.app.trycatch.mapper.point.PointDetailsMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class PointMapperTests {
    @Autowired
    private PointDetailsMapper pointDetailsMapper;

    // 목록 조회
    @Test
    public void testSelectAllByIndividualMemberId() {
        List<PointDetailsDTO> list = pointDetailsMapper.selectAllByIndividualMemberId(7L);
        list.forEach(dto -> log.info("{}......................", dto));
    }

    // 포인트 적립 추가
    @Test
    public void testInsertEarn() {
        PointDetailsVO vo = PointDetailsVO.builder()
                .individualMemberId(7L)
                .pointType(PointType.EARN)
                .pointAmount(1000)
                .remainingPointAmount(1000)
                .paymentAmount(1100)
                .expireDatetime("2031-02-25 00:00:00")
                .build();

        pointDetailsMapper.insert(vo);
        log.info("포인트 적립 완료 - {}......................", vo);
    }

    // 포인트 사용 추가
    @Test
    public void testInsertUse() {
        PointDetailsVO vo = PointDetailsVO.builder()
                .individualMemberId(7L)
                .pointType(PointType.USE)
                .pointAmount(100)
                .remainingPointAmount(0)
                .paymentAmount(0)
                .build();

        pointDetailsMapper.insert(vo);
        log.info("포인트 사용 완료 - {}......................", vo);
    }
}
