package com.app.trycatch.repository.qna;

import com.app.trycatch.domain.qna.QnaCommentVO;
import com.app.trycatch.dto.qna.QnaCommentDTO;
import com.app.trycatch.mapper.qna.QnaCommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QnaCommentDAO {
    private final QnaCommentMapper qnaCommentMapper;

    public void save(QnaCommentVO qnaCommentVO) {
        qnaCommentMapper.insert(qnaCommentVO);
    }

    public List<QnaCommentDTO> findByQnaId(Long qnaId) {
        return qnaCommentMapper.selectByQnaId(qnaId);
    }

    public void delete(Long id, Long memberId) {
        qnaCommentMapper.delete(id, memberId);
    }

    public void update(QnaCommentVO qnaCommentVO) {
        qnaCommentMapper.update(qnaCommentVO);
    }

    public boolean existsByQnaIdAndMemberId(Long qnaId, Long memberId) {
        return qnaCommentMapper.existsByQnaIdAndMemberId(qnaId, memberId);
    }

    public Long findMemberIdById(Long id) {
        return qnaCommentMapper.selectMemberIdById(id);
    }
}
