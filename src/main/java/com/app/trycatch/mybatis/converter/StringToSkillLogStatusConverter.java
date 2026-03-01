package com.app.trycatch.mybatis.converter;


import com.app.trycatch.common.enumeration.member.Status;
import com.app.trycatch.common.enumeration.skillLog.SkillLogStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToSkillLogStatusConverter implements Converter<String, SkillLogStatus> {
    @Override
    public SkillLogStatus convert(String source) {
        return SkillLogStatus.getSkillLogStatus(source);
    }
}
