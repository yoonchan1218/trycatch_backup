package com.app.trycatch.repository.qna;

import com.app.trycatch.domain.qna.QnaCommentLikesVO;
import com.app.trycatch.mapper.qna.QnaCommentLikesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QnaCommentLikesDAO {
    private final QnaCommentLikesMapper qnaCommentLikesMapper;

    public void save(QnaCommentLikesVO qnaCommentLikesVO) {
        qnaCommentLikesMapper.insert(qnaCommentLikesVO);
    }

    public void delete(Long id) {
        qnaCommentLikesMapper.delete(id);
    }

    public int countByCommentId(Long commentId) {
        return qnaCommentLikesMapper.countByCommentId(commentId);
    }
}
