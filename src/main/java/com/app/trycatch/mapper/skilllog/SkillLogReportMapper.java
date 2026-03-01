package com.app.trycatch.mapper.skilllog;

import com.app.trycatch.domain.skilllog.SkillLogReportVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SkillLogReportMapper {
    public void insert(SkillLogReportVO skillLogReportVO);
}
