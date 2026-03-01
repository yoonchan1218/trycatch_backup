package com.app.trycatch.mybatis.converter;


import com.app.trycatch.common.enumeration.file.FileContentType;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

@Component
public class StringToFileContentTypeConverter implements Converter<String, FileContentType> {
    @Override
    public FileContentType convert(String source) {
        return FileContentType.getFileContentType(source);
    }
}
