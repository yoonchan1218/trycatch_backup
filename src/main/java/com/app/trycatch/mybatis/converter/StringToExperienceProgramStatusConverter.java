package com.app.trycatch.mybatis.converter;


import com.app.trycatch.common.enumeration.experience.ExperienceProgramStatus;
import com.app.trycatch.common.enumeration.file.FileContentType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToExperienceProgramStatusConverter implements Converter<String, ExperienceProgramStatus> {
    @Override
    public ExperienceProgramStatus convert(String source) {
        return ExperienceProgramStatus.getExperienceProgramStatus(source);
    }
}
