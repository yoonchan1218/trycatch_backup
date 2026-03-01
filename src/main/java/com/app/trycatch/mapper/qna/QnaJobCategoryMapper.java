package com.app.trycatch.mapper.qna;

import com.app.trycatch.domain.qna.QnaJobCategoryBigVO;
import com.app.trycatch.domain.qna.QnaJobCategorySmallVO;
import com.app.trycatch.domain.qna.QnaJobCategoryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QnaJobCategoryMapper {
    @Select("select * from tbl_qna_job_category_big")
    public List<QnaJobCategoryBigVO> selectAllBig();

    @Select("select * from tbl_qna_job_category")
    public List<QnaJobCategoryVO> selectAll();

    @Select("select * from tbl_qna_job_category_small")
    public List<QnaJobCategorySmallVO> selectAllSmall();

    @Select("select id from tbl_qna_job_category_small where job_category_small_code = #{code}")
    Long selectIdByCode(@Param("code") Long code);
}
