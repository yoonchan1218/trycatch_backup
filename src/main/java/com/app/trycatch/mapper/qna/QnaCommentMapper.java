package com.app.trycatch.mapper.qna;

import com.app.trycatch.domain.qna.QnaCommentVO;
import com.app.trycatch.dto.qna.QnaCommentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QnaCommentMapper {
    void insert(QnaCommentVO qnaCommentVO);
    List<QnaCommentDTO> selectByQnaId(Long qnaId);
    void delete(@Param("id") Long id, @Param("memberId") Long memberId);
    void update(QnaCommentVO qnaCommentVO);
    boolean existsByQnaIdAndMemberId(@Param("qnaId") Long qnaId, @Param("memberId") Long memberId);
    Long selectMemberIdById(Long id);
}
