package sample.cafekiosk.spring.api.service.mail;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sample.cafekiosk.spring.client.MailSendClient;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistory;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistoryRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class MailServiceTest_v1 {

    @DisplayName("메일 전송 테스트_v1")
    @Test
    void sendMail() {
        //given
        MailSendClient mailSendClient = Mockito.mock(MailSendClient.class);
        MailSendHistoryRepository mailSendHistoryRepository = Mockito.mock(MailSendHistoryRepository.class);

        MailService mailService = new MailService(mailSendClient, mailSendHistoryRepository);
        when(mailSendClient.sendMail(any(String.class), any(String.class), any(String.class), any(String.class))).thenReturn(true);
        //Mockito.when(mailSendHistoryRepository.save(any(MailSendHistory.class))).thenReturn(null);


        //when
        boolean result = mailService.sendMail(anyString(), anyString(), anyString(), anyString());

        //then
        assertThat(result).isTrue();

        // verify
        Mockito.verify(mailSendHistoryRepository, times(1)).save(any(MailSendHistory.class));
    }
}