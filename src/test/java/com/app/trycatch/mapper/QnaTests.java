package com.app.trycatch.mapper;

import com.app.trycatch.common.enumeration.qna.QnaStatus;
import com.app.trycatch.dto.qna.QnaCommentDTO;
import com.app.trycatch.dto.qna.QnaDTO;
import com.app.trycatch.mapper.qna.QnaCommentMapper;
import com.app.trycatch.mapper.qna.QnaMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class QnaTests {
    @Autowired
    private QnaMapper qnaMapper;
    @Autowired
    private QnaCommentMapper qnaCommentMapper;

    @Test
    public void testInsert(){
        QnaDTO qnaDTO = new QnaDTO();
        qnaDTO.setIndividualMemberId(4L);
        qnaDTO.setQnaContent("테스트1");
        qnaDTO.setQnaTitle("테스트제목1");
        qnaDTO.setQnaStatus(QnaStatus.PUBLISHED);
        qnaMapper.insert(qnaDTO.toQnaVO());
    }

    @Test
    public void testInsertComment(){
        QnaCommentDTO qnaCommentDTO = new QnaCommentDTO();
        qnaCommentDTO.setQnaId(1L);
        qnaCommentDTO.setQnaCommentContent("테스트댓글1");
        qnaCommentMapper.insert(qnaCommentDTO.toQnaCommentVO());
    }
}
