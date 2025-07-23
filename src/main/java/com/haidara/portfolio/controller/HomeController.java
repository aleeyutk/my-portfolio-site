package com.haidara.portfolio.controller;

import com.haidara.portfolio.dto.ContactForm;
import com.haidara.portfolio.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    
    private final EmailService emailService;
    
    public HomeController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("contactForm", new ContactForm());
        return "index";
    }

    @PostMapping("/contact")
    public String handleContact(
            @Valid ContactForm contactForm,
            BindingResult bindingResult,
            Model model) {
        
        if (bindingResult.hasErrors()) {
            return "index";
        }
        
        try {
            emailService.sendContactEmail(
                contactForm.getName(),
                contactForm.getEmail(),
                contactForm.getMessage()
            );
            model.addAttribute("success", true);
            model.addAttribute("contactForm", new ContactForm()); // Reset form
        } catch (Exception e) {
            model.addAttribute("error", "Failed to send message. Please try again later.");
        }
        
        return "index";
    }
}