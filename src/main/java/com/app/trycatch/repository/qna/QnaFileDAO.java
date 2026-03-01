package com.app.trycatch.repository.qna;

import com.app.trycatch.mapper.qna.QnaFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QnaFileDAO {
    private final QnaFileMapper qnaFileMapper;

    public void save(Long id, Long qnaId) {
        qnaFileMapper.insert(id, qnaId);
    }

    public void deleteById(Long fileId, Long qnaId) {
        qnaFileMapper.deleteById(fileId, qnaId);
    }
}
