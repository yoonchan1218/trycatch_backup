package com.app.trycatch.mybatis.converter;


import com.app.trycatch.common.enumeration.member.Status;
import com.app.trycatch.common.enumeration.skillLog.SkillLogReportTargetType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToSkillLogReportTargetTypeConverter implements Converter<String, SkillLogReportTargetType> {
    @Override
    public SkillLogReportTargetType convert(String source) {
        return SkillLogReportTargetType.getSkillLogReportTargetType(source);
    }
}
