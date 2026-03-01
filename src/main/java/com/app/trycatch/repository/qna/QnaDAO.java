package com.app.trycatch.repository.qna;

import com.app.trycatch.common.pagination.Criteria;
import com.app.trycatch.domain.qna.QnaVO;
import com.app.trycatch.dto.qna.QnaDTO;
import com.app.trycatch.mapper.qna.QnaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QnaDAO {
    private final QnaMapper qnaMapper;

//    작성
    public void save(QnaVO qnaVO) {
        qnaMapper.insert(qnaVO);
    }

//    수정
    public void update(QnaVO qnaVO) {
        qnaMapper.update(qnaVO);
    }

//    목록 (페이징)
    public List<QnaDTO> findAll(Criteria criteria, int sort, String keyword) {
        return qnaMapper.selectAll(criteria, sort, keyword);
    }

//    원글 작성자 ID 조회
    public Long findMemberIdById(Long id) {
        return qnaMapper.selectMemberIdById(id);
    }

//    전체 개수
    public int findTotal(String keyword) {
        return qnaMapper.selectTotal(keyword);
    }

//    인기글 (조회수 Top N)
    public List<QnaDTO> findTopByViewCount(int limit) {
        return qnaMapper.selectTopByViewCount(limit);
    }

//    최신글 (최근 N개)
    public List<QnaDTO> findLatest(int limit) {
        return qnaMapper.selectLatest(limit);
    }

}
