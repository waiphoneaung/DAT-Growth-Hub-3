package com.g3.elis.util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public List<EmailSendResult> sendEmail(List<String> recipientEmails, String subject, String body) {
        List<EmailSendResult> results = new ArrayList<>();
        for (String recipientEmail : recipientEmails) {
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(recipientEmail);
                message.setSubject(subject);
                message.setText(body);
                mailSender.send(message);
                results.add(new EmailSendResult(recipientEmail, "Success"));
            } catch (Exception e) {
                results.add(new EmailSendResult(recipientEmail, "Failed: " + e.getMessage()));
            }
        }
        return results;
    }
    
    public void sendOtpEmail(String to, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Your OTP Code");
        message.setText("Dear User, \nYour OTP code is: " + otp+ "\n OTP Code will be expired in 15 minutes.");
        mailSender.send(message);
    }
}
