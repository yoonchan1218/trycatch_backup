package com.app.trycatch.repository.qna;

import com.app.trycatch.mapper.qna.QnaCommentFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QnaCommentFileDAO {
    private final QnaCommentFileMapper qnaCommentFileMapper;

    public void save(Long id, Long qnaCommentId) {
        qnaCommentFileMapper.insert(id, qnaCommentId);
    }

    public Long findFileIdByCommentId(Long qnaCommentId) {
        return qnaCommentFileMapper.selectFileIdByCommentId(qnaCommentId);
    }

    public void deleteByCommentId(Long qnaCommentId) {
        qnaCommentFileMapper.deleteByCommentId(qnaCommentId);
    }
}
