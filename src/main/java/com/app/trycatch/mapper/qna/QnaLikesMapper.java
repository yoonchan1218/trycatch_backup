package com.app.trycatch.mapper.qna;

import com.app.trycatch.domain.qna.QnaLikesVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QnaLikesMapper {
    void insert(QnaLikesVO qnaLikesVO);
    void delete(Long id);
    void deleteByMemberAndQna(Long memberId, Long qnaId);
    int countByQnaId(Long qnaId);
    int existsByMemberAndQna(Long memberId, Long qnaId);
}
