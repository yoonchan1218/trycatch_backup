package com.app.trycatch.mapper.qna;

import com.app.trycatch.common.pagination.Criteria;
import com.app.trycatch.domain.qna.QnaVO;
import com.app.trycatch.dto.qna.CorpNameKeywordDTO;
import com.app.trycatch.dto.qna.QnaDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface QnaMapper {
    void insert(QnaVO qnaVO);
    QnaDTO selectById(Long id);
    List<QnaDTO> selectAll(@Param("criteria") Criteria criteria, @Param("sort") int sort, @Param("keyword") String keyword);
    int selectTotal(@Param("keyword") String keyword);
    void increaseViewCount(Long id);
    void update(QnaVO qnaVO);
    void delete(Long id);
    void updateFileId(@Param("id") Long id, @Param("fileId") Long fileId);
    Long selectMemberIdById(Long id);
    List<CorpNameKeywordDTO> selectCorpByKeyword(String keyword);
    List<QnaDTO> selectTopByViewCount(@Param("limit") int limit);
    List<QnaDTO> selectLatest(@Param("limit") int limit);
}
