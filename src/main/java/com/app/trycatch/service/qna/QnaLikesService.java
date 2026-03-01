package com.app.trycatch.service.qna;

import com.app.trycatch.domain.qna.QnaLikesVO;
import com.app.trycatch.repository.qna.QnaLikesDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QnaLikesService {
    private final QnaLikesDAO qnaLikesDAO;

    public void toggleLike(Long memberId, Long qnaId) {
        if (qnaLikesDAO.existsByMemberAndQna(memberId, qnaId)) {
            qnaLikesDAO.deleteByMemberAndQna(memberId, qnaId);
        } else {
            qnaLikesDAO.save(QnaLikesVO.builder()
                    .memberId(memberId)
                    .qnaId(qnaId)
                    .build());
        }
    }
}
