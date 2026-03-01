package com.app.trycatch.repository.qna;

import com.app.trycatch.domain.qna.QnaLikesVO;
import com.app.trycatch.mapper.qna.QnaLikesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QnaLikesDAO {
    private final QnaLikesMapper qnaLikesMapper;

    public void save(QnaLikesVO qnaLikesVO) {
        qnaLikesMapper.insert(qnaLikesVO);
    }

    public void delete(Long id) {
        qnaLikesMapper.delete(id);
    }

    public void deleteByMemberAndQna(Long memberId, Long qnaId) {
        qnaLikesMapper.deleteByMemberAndQna(memberId, qnaId);
    }

    public boolean existsByMemberAndQna(Long memberId, Long qnaId) {
        return qnaLikesMapper.existsByMemberAndQna(memberId, qnaId) > 0;
    }

    public int countByQnaId(Long qnaId) {
        return qnaLikesMapper.countByQnaId(qnaId);
    }
}
