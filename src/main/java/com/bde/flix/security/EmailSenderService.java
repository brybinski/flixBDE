package com.bde.flix.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    @Value("${spring.mail.username}")
    String sender;

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String recep,
                          String subj,
                          String body){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(sender);
        message.setTo(recep);
        message.setSubject(subj);
        message.setText(body);
        mailSender.send(message);
    }
}
