package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String toEmail,
                                String subject,
                                String body
    ) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nogbuituananh@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
    }

    public void sendAsHTML(String toEmail,
                           String subject,
                           String body
    ) throws MessagingException {
        MimeMessage mail = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail);
        helper.setSubject(subject);
        try {
            helper.setFrom(new InternetAddress("ngobutuananh@gmail.com", "Article API Team"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
//        helper.setFrom("quachdieukhanhfpttrainee@gmail.com");
        helper.setTo(toEmail);
        boolean html = true;
        helper.setText(body, html);
        mailSender.send(mail);
    }

}
