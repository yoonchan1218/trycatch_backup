package com.app.trycatch.mapper.qna;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface QnaFileMapper {
    void insert(@Param("id") Long id, @Param("qnaId") Long qnaId);
    void deleteById(@Param("fileId") Long fileId, @Param("qnaId") Long qnaId);
}
