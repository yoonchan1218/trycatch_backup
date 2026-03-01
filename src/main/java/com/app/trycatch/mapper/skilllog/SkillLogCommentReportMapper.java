package com.app.trycatch.mapper.skilllog;

import com.app.trycatch.domain.skilllog.SkillLogCommentReportVO;
import com.app.trycatch.domain.skilllog.SkillLogReportVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SkillLogCommentReportMapper {
    public void insert(SkillLogCommentReportVO skillLogCommentReportVO);
}
