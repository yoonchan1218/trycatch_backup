package com.app.trycatch.mybatis.converter;


import com.app.trycatch.common.enumeration.experience.ChallengerStatus;
import com.app.trycatch.common.enumeration.member.Status;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToChallengerStatusConverter implements Converter<String, ChallengerStatus> {
    @Override
    public ChallengerStatus convert(String source) {
        return ChallengerStatus.getChallengerStatus(source);
    }
}
