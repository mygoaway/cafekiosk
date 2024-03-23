package com.example.storage.domain.history.mail;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MailSendHistoryRepository extends JpaRepository<MailSendHistory, Long> {
    List<MailSendHistory> findAllByToEmail(String toEmail);

}
