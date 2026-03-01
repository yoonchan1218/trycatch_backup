package com.app.trycatch.mapper;

import com.app.trycatch.domain.qna.QnaJobCategoryBigVO;
import com.app.trycatch.domain.qna.QnaJobCategorySmallVO;
import com.app.trycatch.dto.qna.QnaJobCategoryBigDTO;
import com.app.trycatch.dto.qna.QnaJobCategoryDTO;
import com.app.trycatch.dto.qna.QnaJobCategorySmallDTO;
import com.app.trycatch.mapper.qna.QnaCommentMapper;
import com.app.trycatch.mapper.qna.QnaJobCategoryMapper;
import com.app.trycatch.mapper.qna.QnaMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QnaCategoryTests {
    @Autowired
    private QnaMapper qnaMapper;
    @Autowired
    private QnaCommentMapper qnaCommentMapper;
    @Autowired
    private QnaJobCategoryMapper qnaJobCategoryMapper;


    @Test
    public void testSelectAll() {
//        qnaJobCategoryMapper.selectAll().forEach(System.out::println);
        qnaJobCategoryMapper.selectAllBig().forEach(System.out::println);
//        qnaJobCategoryMapper.selectAllSmall().forEach(System.out::println);
    }
}
