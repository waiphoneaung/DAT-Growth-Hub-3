package com.g3.elis.util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.g3.elis.repository.SentEmailRepository;
import com.g3.elis.service.SentEmail;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SentEmailRepository sendEmailRepository;
    
    public List<EmailSendResult> sendEmail(List<String> recipientEmails, String subject, String body) {
       List<EmailSendResult> results = new ArrayList<>();
    for (String recipientEmail : recipientEmails) {
         
//    	StringJoiner emailJoiner = new StringJoiner(", ");
//        for (String email : recipientEmails) {
//            emailJoiner.add(email);
//        
//        String joinedEmails = emailJoiner.toString();

        	
        	try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(recipientEmail);
                message.setSubject(subject);
                message.setText(body);
                mailSender.send(message);
                results.add(new EmailSendResult(recipientEmail, "Success"));
               
                // Save sent email to the database
                SentEmail sentEmail = new SentEmail(recipientEmail, subject, body, new Timestamp(System.currentTimeMillis()));
                sendEmailRepository.save(sentEmail);

            } catch (Exception e) {
                results.add(new EmailSendResult(recipientEmail, "Failed: " + e.getMessage()));
            }
        }
        return results;
           }
    
    public void sendOtpEmail(String to, String otp) {
        String subject = "Your OTP Code";
        String body = "Dear User, \nYour OTP code is: " + otp + "\n OTP Code will expire in 15 minutes.";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);

       
    }
}