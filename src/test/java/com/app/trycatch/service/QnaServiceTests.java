package com.app.trycatch.service;

import com.app.trycatch.domain.qna.QnaLikesVO;
import com.app.trycatch.dto.qna.QnaDTO;
import com.app.trycatch.dto.qna.QnaWithPagingDTO;
import com.app.trycatch.mapper.qna.QnaLikesMapper;
import com.app.trycatch.service.qna.QnaService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class QnaServiceTests {

    @Autowired
    private QnaService qnaService;

    @Autowired
    private QnaLikesMapper qnaLikesMapper;

    private static final Long TEST_MEMBER_ID = 4L;
    private static final Long TEST_QNA_ID = 1L;

    @Test
    public void testList_최신순() {
        QnaWithPagingDTO result = qnaService.list(1, 1, "테스트");

        log.info("최신순 total={}, qna 수={}", result.getTotal(), result.getQnas().size());
        result.getQnas().forEach(q -> log.info("  최신순: id={}, title={}", q.getId(), q.getQnaTitle()));
    }

    @Test
    public void testList_인기순() {
        QnaWithPagingDTO result = qnaService.list(1, 3, "테스트");
        log.info("조회수순 total={}, qna 수={}", result.getTotal(), result.getQnas().size());
        result.getQnas().forEach(q -> log.info("  조회수순: id={}, title={}, view={}", q.getId(), q.getQnaTitle(), q.getQnaViewCount()));
    }

    @Test
    public void testDetail_비로그인() {
        QnaDTO qna = qnaService.detail(TEST_QNA_ID, null);
        log.info("비로그인 detail: id={}, likedByCurrentUser={}", qna.getId(), qna.isLikedByCurrentUser());
        // likedByCurrentUser == false 이어야 함
    }

    @Test
    public void testDetail_로그인_좋아요없을때() {
        qnaLikesMapper.deleteByMemberAndQna(TEST_MEMBER_ID, TEST_QNA_ID); // 좋아요 없는 상태 보장
        QnaDTO qna = qnaService.detail(TEST_QNA_ID, TEST_MEMBER_ID);
        log.info("로그인(좋아요 없을때) likedByCurrentUser={} (false이어야 함)", qna.isLikedByCurrentUser());
    }

    @Test
    public void testDetail_로그인_좋아요있을때() {
        qnaLikesMapper.deleteByMemberAndQna(TEST_MEMBER_ID, TEST_QNA_ID);
        qnaLikesMapper.insert(QnaLikesVO.builder().memberId(TEST_MEMBER_ID).qnaId(TEST_QNA_ID).build());
        QnaDTO qna = qnaService.detail(TEST_QNA_ID, TEST_MEMBER_ID);
        log.info("로그인(좋아요 있을때) likedByCurrentUser={} (true이어야 함)", qna.isLikedByCurrentUser());
        qnaLikesMapper.deleteByMemberAndQna(TEST_MEMBER_ID, TEST_QNA_ID); // 정리
    }

    @Test
    public void testToggleLike_추가() {
        qnaLikesMapper.deleteByMemberAndQna(TEST_MEMBER_ID, TEST_QNA_ID); // 없는 상태에서 시작
        int countBefore = qnaLikesMapper.countByQnaId(TEST_QNA_ID);
        int countAfter = qnaService.toggleLike(TEST_MEMBER_ID, TEST_QNA_ID);
        log.info("toggleLike 추가: before={}, after={} (after가 before+1 이어야 함)", countBefore, countAfter);
    }

    @Test
    public void testToggleLike_취소() {
        // 좋아요 있는 상태에서 시작
        qnaLikesMapper.deleteByMemberAndQna(TEST_MEMBER_ID, TEST_QNA_ID);
        qnaLikesMapper.insert(QnaLikesVO.builder().memberId(TEST_MEMBER_ID).qnaId(TEST_QNA_ID).build());
        int countBefore = qnaLikesMapper.countByQnaId(TEST_QNA_ID);
        int countAfter = qnaService.toggleLike(TEST_MEMBER_ID, TEST_QNA_ID); // 이미 있으므로 삭제됨
        log.info("toggleLike 취소: before={}, after={} (after가 before-1 이어야 함)", countBefore, countAfter);
    }

    @Test
    public void testDelete_본인글() {
        // TEST_QNA_ID 게시글의 작성자 memberId로 삭제
        // 주의: 이 테스트 실행 후 해당 게시글은 deleted 상태가 됨
        qnaService.delete(TEST_MEMBER_ID, TEST_QNA_ID);
        log.info("본인글 delete 완료 (selectById 결과가 null이면 정상)");
    }

    @Test
    public void testDelete_타인글() {
        Long otherMemberId = 999L; // DB에 없는 다른 멤버 ID
        qnaService.delete(otherMemberId, TEST_QNA_ID);
        log.info("타인글 delete 시도 완료 (게시글이 그대로 published 상태여야 함)");
    }
}
