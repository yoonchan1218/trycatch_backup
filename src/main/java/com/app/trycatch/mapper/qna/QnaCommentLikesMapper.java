package com.app.trycatch.mapper.qna;

import com.app.trycatch.domain.qna.QnaCommentLikesVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QnaCommentLikesMapper {
    void insert(QnaCommentLikesVO qnaCommentLikesVO);
    void delete(Long id);
    int countByCommentId(Long commentId);
}
