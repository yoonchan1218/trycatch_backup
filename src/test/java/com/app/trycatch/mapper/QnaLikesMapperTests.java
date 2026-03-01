package com.app.trycatch.mapper;

import com.app.trycatch.domain.qna.QnaLikesVO;
import com.app.trycatch.mapper.qna.QnaLikesMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class QnaLikesMapperTests {

    @Autowired
    private QnaLikesMapper qnaLikesMapper;

    // memberId=4, qnaId=1 기준 (DB에 실제 존재하는 값 사용)
    private static final Long TEST_MEMBER_ID = 4L;
    private static final Long TEST_QNA_ID = 1L;

    @Test
    public void testExistsByMemberAndQna_없을때() {
        // 테스트 전 혹시 있다면 삭제
        qnaLikesMapper.deleteByMemberAndQna(TEST_MEMBER_ID, TEST_QNA_ID);
        int result = qnaLikesMapper.existsByMemberAndQna(TEST_MEMBER_ID, TEST_QNA_ID);
        log.info("좋아요 없을때 exists 결과(0이어야 함): {}", result);
    }

    @Test
    public void testInsert() {
        qnaLikesMapper.deleteByMemberAndQna(TEST_MEMBER_ID, TEST_QNA_ID); // 중복 방지
        QnaLikesVO vo = QnaLikesVO.builder()
                .memberId(TEST_MEMBER_ID)
                .qnaId(TEST_QNA_ID)
                .build();
        qnaLikesMapper.insert(vo);
        log.info("좋아요 insert 완료. 삽입된 id: {}", vo.getId());
    }

    @Test
    public void testExistsByMemberAndQna_있을때() {
        qnaLikesMapper.deleteByMemberAndQna(TEST_MEMBER_ID, TEST_QNA_ID);
        qnaLikesMapper.insert(QnaLikesVO.builder().memberId(TEST_MEMBER_ID).qnaId(TEST_QNA_ID).build());
        int result = qnaLikesMapper.existsByMemberAndQna(TEST_MEMBER_ID, TEST_QNA_ID);
        log.info("좋아요 있을때 exists 결과(1이어야 함): {}", result);
        // 정리
        qnaLikesMapper.deleteByMemberAndQna(TEST_MEMBER_ID, TEST_QNA_ID);
    }

    @Test
    public void testCountByQnaId() {
        int count = qnaLikesMapper.countByQnaId(TEST_QNA_ID);
        log.info("qnaId={} 의 좋아요 수: {}", TEST_QNA_ID, count);
    }

    @Test
    public void testDeleteByMemberAndQna() {
        // insert 후 delete
        qnaLikesMapper.insert(QnaLikesVO.builder().memberId(TEST_MEMBER_ID).qnaId(TEST_QNA_ID).build());
        int before = qnaLikesMapper.existsByMemberAndQna(TEST_MEMBER_ID, TEST_QNA_ID);
        log.info("삭제 전 exists(1이어야 함): {}", before);
        qnaLikesMapper.deleteByMemberAndQna(TEST_MEMBER_ID, TEST_QNA_ID);
        int after = qnaLikesMapper.existsByMemberAndQna(TEST_MEMBER_ID, TEST_QNA_ID);
        log.info("삭제 후 exists(0이어야 함): {}", after);
    }
}
