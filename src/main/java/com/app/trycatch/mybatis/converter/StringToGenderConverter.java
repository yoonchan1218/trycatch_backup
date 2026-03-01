package com.app.trycatch.mybatis.converter;




import com.app.trycatch.common.enumeration.member.Gender;
import com.app.trycatch.common.enumeration.member.Provider;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToGenderConverter implements Converter<String, Gender> {
    @Override
    public Gender convert(String source) {
        return Gender.getGender(source);
    }
}
