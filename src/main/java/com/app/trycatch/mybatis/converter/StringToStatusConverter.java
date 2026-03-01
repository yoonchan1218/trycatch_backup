package com.app.trycatch.mybatis.converter;


import com.app.trycatch.common.enumeration.member.Status;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToStatusConverter implements Converter<String, Status> {
    @Override
    public Status convert(String source) {
        return Status.getStatus(source);
    }
}
