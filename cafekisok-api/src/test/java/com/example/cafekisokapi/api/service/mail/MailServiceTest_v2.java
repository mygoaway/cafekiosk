package com.example.cafekisokapi.api.service.mail;


import com.example.cafekisokapi.mail.service.MailSendClient;
import com.example.cafekisokapi.mail.service.MailService;
import com.example.storage.domain.history.mail.MailSendHistory;
import com.example.storage.domain.history.mail.MailSendHistoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MailServiceTest_v2 {

    @InjectMocks private MailService mailService;
    @Mock private MailSendClient mailSendClient;
    @Mock private MailSendHistoryRepository mailSendHistoryRepository;

    @DisplayName("메일 전송 테스트_v2")
    @Test
    void sendMail() {
        //given
        when(mailSendClient.sendMail(any(String.class), any(String.class), any(String.class), any(String.class))).thenReturn(true);

        //when
        boolean result = mailService.sendMail(anyString(), anyString(), anyString(), anyString());

        //then
        assertThat(result).isTrue();

        // verify
        Mockito.verify(mailSendHistoryRepository, times(1)).save(any(MailSendHistory.class));
    }
}