package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendWelcomeEmail(String toEmail, String username) {
        String subject = "Welcome to Our Platform!";
        String message = "Hi " + username + ",\n\nWelcome to our platform! We're excited to have you.\n\nBest,\nTeam";

        sendEmail(toEmail, subject, message);
    }

    @Async
    public void sendOrderConfirmationEmail(String toEmail, String orderDetails) {
        String subject = "Order Confirmation";
        String message = "Dear Customer,\n\nYour order has been placed successfully.\n\nOrder Details:\n" + orderDetails + "\n\nThank you for shopping with us!\n\nBest,\nTeam";

        sendEmail(toEmail, subject, message);
    }

	@Async
    private void sendEmail(String toEmail, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailSender.send(mailMessage);
        System.out.println("Email sent to: " + toEmail);
    }
}

