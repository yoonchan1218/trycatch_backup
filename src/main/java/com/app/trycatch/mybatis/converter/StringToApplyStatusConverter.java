package com.app.trycatch.mybatis.converter;


import com.app.trycatch.common.enumeration.experience.ApplyStatus;
import com.app.trycatch.common.enumeration.experience.ExperienceProgramStatus;
import com.app.trycatch.common.enumeration.file.FileContentType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToApplyStatusConverter implements Converter<String, ApplyStatus> {
    @Override
    public ApplyStatus convert(String source) {
        return ApplyStatus.getApplyStatus(source);
    }
}
