package com.akm.sendemailspringboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
 
    @Autowired
    private JavaMailSender mailSender;
    @Value("${emails.sender_email}")
    private String senderEmail;
    
    @Value("${emails.sender_name}")
    private String senderName;
    
    @Value("${emails.sender_address}")
    private String senderAddress;

    public void sendEmail(String receivedEmail, String subject, String content) {
    	
    try {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setTo(receivedEmail);
        message.setSubject(subject);
        message.setText(content);
        
        mailSender.send(message);
    	}
    	catch(Exception e) {
    		System.out.println("Exception in stmpSenderEmail");
    		e.printStackTrace();
    	}
    }
}