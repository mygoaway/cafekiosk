package sample.cafekiosk.spring.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MailSendClient {

    public boolean sendMail(String formEmail, String toEmail, String subject, String contents) {
        log.info("메일 전송");
        throw new IllegalArgumentException("메일 전송");
    }
}

