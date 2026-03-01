package com.app.trycatch.controller.sms;


import com.app.trycatch.dto.member.MemberDTO;
import com.app.trycatch.service.sms.SmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SmsController {
    private final SmsService smsService;

    @PostMapping("/api/messages/send")
    @ResponseBody
    public String sendSms(@RequestBody MemberDTO memberDTO){
        log.info("{}.............", memberDTO.getMemberPhone());
        return smsService.sendSms(memberDTO.getMemberPhone());
    }
}














