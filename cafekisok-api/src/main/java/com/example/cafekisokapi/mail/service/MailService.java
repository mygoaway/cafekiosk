package com.example.cafekisokapi.mail.service;

import com.example.storage.domain.history.mail.MailSendHistory;
import com.example.storage.domain.history.mail.MailSendHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MailService {

    private final MailSendClient mailSendClient;
    private final MailSendHistoryRepository mailSendHistoryRepository;

    public boolean sendMail(String fromEmail, String toEmail, String subject, String contents) {
        boolean result = mailSendClient.sendMail(fromEmail, toEmail, subject, contents);

        if(result) {
            mailSendHistoryRepository.save(MailSendHistory.builder()
                    .fromEmail(fromEmail)
                    .toEmail(toEmail)
                    .subject(subject)
                    .contents(contents)
                    .build()
            );
            return true;
        }

        return false;
    }
}
