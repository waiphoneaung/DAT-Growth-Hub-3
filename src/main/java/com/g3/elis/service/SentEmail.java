package com.g3.elis.service;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sent_emails")
public class SentEmail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String recipientEmail;
    private String subject;
   
    
    @Column( columnDefinition ="TEXT")
    private String body;
    private Timestamp sentAt;

    public SentEmail() {}

    public SentEmail(String recipientEmail, String subject, String body, Timestamp sentAt) {
        this.recipientEmail = recipientEmail;
        this.subject = subject;
        this.body = body;
        this.sentAt = sentAt;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRecipientEmail() {
		return recipientEmail;
	}

	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Timestamp getSentAt() {
		return sentAt;
	}

	public void setSentAt(Timestamp sentAt) {
		this.sentAt = sentAt;
	}

	public SentEmail(int id, String recipientEmail, String subject, String body, Timestamp sentAt) {
		super();
		this.id = id;
		this.recipientEmail = recipientEmail;
		this.subject = subject;
		this.body = body;
		this.sentAt = sentAt;
	}

  
}
