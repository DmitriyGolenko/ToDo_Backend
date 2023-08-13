package com.app.TODO_backend.service;


import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;

    public void sendMailMessage(String to, String subject, String message){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("tt7545946@gmail.com");
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(message);
        javaMailSender.send(mail);

    }
}
