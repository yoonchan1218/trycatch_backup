package com.app.trycatch.service;

import com.app.trycatch.common.advice.GlobalModelAttributes;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class GlobalModelAttributesTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GlobalModelAttributes globalModelAttributes;

    @Test
    public void testBean_등록확인() {
        log.info("GlobalModelAttributes bean: {}", globalModelAttributes);
        assert globalModelAttributes != null : "GlobalModelAttributes bean이 등록되어야 합니다";
    }

    @Test
    public void testRequestURI_모델에_포함() throws Exception {
        // /corporate/home 요청 시 model에 requestURI가 포함되는지 확인
        // (로그인 안하면 redirect 되므로 redirect 여부만 확인)
        mockMvc.perform(get("/corporate/home"))
                .andExpect(status().is3xxRedirection())
                .andDo(result -> log.info("요청 결과: status={}, redirectUrl={}",
                        result.getResponse().getStatus(),
                        result.getResponse().getRedirectedUrl()));
    }

    @Test
    public void testRequestURI_메인페이지() throws Exception {
        // 메인 페이지 요청 시 requestURI 모델 속성 확인
        mockMvc.perform(get("/main/main"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("requestURI"))
                .andExpect(model().attribute("requestURI", "/main/main"))
                .andDo(result -> log.info("requestURI 모델 속성 확인 완료"));
    }
}
