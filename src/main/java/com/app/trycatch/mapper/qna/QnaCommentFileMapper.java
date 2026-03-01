package com.app.trycatch.mapper.qna;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface QnaCommentFileMapper {
    void insert(@Param("id") Long id, @Param("qnaCommentId") Long qnaCommentId);
    Long selectFileIdByCommentId(@Param("qnaCommentId") Long qnaCommentId);
    void deleteByCommentId(@Param("qnaCommentId") Long qnaCommentId);
}
