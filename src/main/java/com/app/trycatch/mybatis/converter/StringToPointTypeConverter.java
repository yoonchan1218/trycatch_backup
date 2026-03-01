package com.app.trycatch.mybatis.converter;


import com.app.trycatch.common.enumeration.point.PointType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToPointTypeConverter implements Converter<String, PointType> {
    @Override
    public PointType convert(String source) {
        return PointType.getPointType(source);
    }
}
