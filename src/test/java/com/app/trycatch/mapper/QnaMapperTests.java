package com.app.trycatch.mapper;

import com.app.trycatch.common.pagination.Criteria;
import com.app.trycatch.dto.qna.QnaDTO;
import com.app.trycatch.mapper.qna.QnaMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class QnaMapperTests {

    @Autowired
    private QnaMapper qnaMapper;

    @Test
    public void testSelectById() {
        QnaDTO qna = qnaMapper.selectById(1L);
        log.info("selectById: {}", qna);
    }

    @Test
    public void testSelectAll_최신순() {
        int total = qnaMapper.selectTotal(null);
        Criteria criteria = new Criteria(1, total);
        List<QnaDTO> qnas = qnaMapper.selectAll(criteria, 1, null);
        qnas.forEach(q -> log.info("최신순: {}", q));
    }

    @Test
    public void testSelectAll_좋아요순() {
        int total = qnaMapper.selectTotal(null);
        Criteria criteria = new Criteria(1, total);
        List<QnaDTO> qnas = qnaMapper.selectAll(criteria, 2, null);
        qnas.forEach(q -> log.info("좋아요순: {}", q));
    }

    @Test
    public void testSelectAll_인기순() {
        int total = qnaMapper.selectTotal(null);
        Criteria criteria = new Criteria(1, total);
        List<QnaDTO> qnas = qnaMapper.selectAll(criteria, 3, null);
        qnas.forEach(q -> log.info("조회수순: {}", q));
    }

    @Test
    public void testSelectAll_댓글순() {
        int total = qnaMapper.selectTotal(null);
        Criteria criteria = new Criteria(1, total);
        List<QnaDTO> qnas = qnaMapper.selectAll(criteria, 4, null);
        qnas.forEach(q -> log.info("댓글순: {}", q));
    }

    @Test
    public void testSelectTopByViewCount() {
        List<QnaDTO> qnas = qnaMapper.selectTopByViewCount(5);
        qnas.forEach(q -> log.info("인기글(조회수): id={}, title={}, viewCount={}", q.getId(), q.getQnaTitle(), q.getQnaViewCount()));
    }

    @Test
    public void testSelectLatest() {
        List<QnaDTO> qnas = qnaMapper.selectLatest(5);
        qnas.forEach(q -> log.info("최신글: id={}, title={}", q.getId(), q.getQnaTitle()));
    }

    @Test
    public void testIncreaseViewCount() {
        QnaDTO before = qnaMapper.selectById(1L);
        log.info("증가 전 조회수: {}", before != null ? before.getQnaViewCount() : "null");
        qnaMapper.increaseViewCount(1L);
        QnaDTO after = qnaMapper.selectById(1L);
        log.info("증가 후 조회수: {}", after != null ? after.getQnaViewCount() : "null");
    }

    @Test
    public void testDelete() {
        qnaMapper.delete(1L);
        QnaDTO qna = qnaMapper.selectById(1L);
        log.info("삭제 후 조회(null이면 정상): {}", qna);
    }
}
