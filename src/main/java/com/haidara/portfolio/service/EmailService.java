package com.yourname.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendContactEmail(String name, String email, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("aliyuatk@gmail.com"); // Your inbox
        mailMessage.setSubject("New Contact Form Submission");
        mailMessage.setText("From: " + name + " <" + email + ">\n\n" + message);

        mailSender.send(mailMessage);
    }
}
