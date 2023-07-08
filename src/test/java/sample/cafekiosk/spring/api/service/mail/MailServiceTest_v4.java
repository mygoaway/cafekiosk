package sample.cafekiosk.spring.api.service.mail;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import sample.cafekiosk.spring.client.MailSendClient;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistory;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistoryRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class MailServiceTest_v4 {

    @InjectMocks private MailService mailService;
    @Mock private MailSendClient mailSendClient;
    @Mock private MailSendHistoryRepository mailSendHistoryRepository;

    @DisplayName("메일 전송 테스트_v4")
    @Test
    void sendMail() {
        //given
        BDDMockito.given(mailSendClient.sendMail(anyString(), anyString(), anyString(), anyString())).willReturn(true);

        //when
        boolean result = mailService.sendMail(anyString(), anyString(), anyString(), anyString());

        //then
        assertThat(result).isTrue();

        // verify
        Mockito.verify(mailSendHistoryRepository, times(1)).save(any(MailSendHistory.class));
    }
}