package com.app.trycatch.service.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;

    // 랜덤 10자리 영문 코드 생성
    public String createCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            // 대소문자 영문 랜덤
            if (random.nextBoolean()) {
                code.append((char) (random.nextInt(26) + 'A'));
            } else {
                code.append((char) (random.nextInt(26) + 'a'));
            }
        }

        return code.toString();
    }

    // 팀원 초대 이메일 발송
    public void sendInviteMail(String receiverEmail, String inviteCode, String corpName) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            helper.setTo(receiverEmail);
            helper.setSubject("[TRY-CATCH] " + corpName + " 팀원 초대");

            String joinLink = "http://localhost:10000/mail/invite/join?code=" + inviteCode;

            String body = "<div style='font-family: Arial, sans-serif; max-width: 600px; margin: 0 auto;'>"
                    + "<h2 style='color: #333;'>TRY-CATCH 팀원 초대</h2>"
                    + "<p>안녕하세요,</p>"
                    + "<p><strong>" + corpName + "</strong>에서 팀원으로 초대했습니다.</p>"
                    + "<p>아래 버튼을 클릭하여 가입해 주세요.</p>"
                    + "<div style='text-align: center; margin: 30px 0;'>"
                    + "<a href='" + joinLink + "' "
                    + "style='background-color: #4A90D9; color: white; padding: 12px 30px; "
                    + "text-decoration: none; border-radius: 5px; font-size: 16px;'>가입하기</a>"
                    + "</div>"
                    + "<p style='color: #999; font-size: 12px;'>본 메일은 TRY-CATCH 서비스에서 발송되었습니다.</p>"
                    + "</div>";

            helper.setText(body, true);
            javaMailSender.send(mimeMessage);
            log.info("초대 메일 발송 완료: to={}, code={}", receiverEmail, inviteCode);

        } catch (MessagingException e) {
            log.error("초대 메일 발송 실패: to={}", receiverEmail, e);
            throw new RuntimeException("메일 발송에 실패했습니다.", e);
        }
    }
}
