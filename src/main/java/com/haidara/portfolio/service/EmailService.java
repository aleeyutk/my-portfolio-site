package com.haidara.portfolio.service;

import com.haidara.portfolio.model.ContactMessage;
import com.haidara.portfolio.repository.ContactMessageRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final ContactMessageRepository repository;

    public EmailService(JavaMailSender mailSender, ContactMessageRepository repository) {
        this.mailSender = mailSender;
        this.repository = repository;
    }

    public void sendContactEmail(String name, String email, String message) {
        // Save to database
        ContactMessage contactMessage = new ContactMessage();
        contactMessage.setName(name);
        contactMessage.setEmail(email);
        contactMessage.setMessage(message);
        repository.save(contactMessage);

        // Send email
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("aliyuatk@gmail.com");
        mailMessage.setSubject("New Contact Form Submission from " + name);
        mailMessage.setText("From: " + name + " <" + email + ">\n\n" + message);
        
        mailSender.send(mailMessage);
    }
}