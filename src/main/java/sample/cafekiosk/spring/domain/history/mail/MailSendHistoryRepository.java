package sample.cafekiosk.spring.domain.history.mail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sample.cafekiosk.spring.domain.order.Order;
import sample.cafekiosk.spring.domain.order.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface MailSendHistoryRepository extends JpaRepository<MailSendHistory, Long> {
    List<MailSendHistory> findAllByToEmail(String toEmail);

}
