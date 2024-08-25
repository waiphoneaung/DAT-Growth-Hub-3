package com.g3.elis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g3.elis.service.SentEmail;


public interface SentEmailRepository extends JpaRepository<SentEmail, Integer> {
    void deleteByRecipientEmailAndSubject(String recipientEmail, String subject);
}
