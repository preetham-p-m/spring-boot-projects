package com.pmp.email_sender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.pmp.email_sender.service.EmailService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/send-email")
    public void sendEmail() {
        emailService.sendEmail("TO_EMAIL_ID", "Test Body", "Test Subject");
    }

}
