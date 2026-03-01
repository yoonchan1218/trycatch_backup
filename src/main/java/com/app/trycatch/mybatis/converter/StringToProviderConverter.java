package com.app.trycatch.mybatis.converter;




import com.app.trycatch.common.enumeration.member.Provider;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToProviderConverter implements Converter<String, Provider> {
    @Override
    public Provider convert(String source) {
        return Provider.getProvider(source);
    }
}
