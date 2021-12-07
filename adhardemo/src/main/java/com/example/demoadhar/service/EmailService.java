package com.example.demoadhar.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.demoadhar.entity.Mail;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class EmailService {

    @Value("${spring.mail.username}")
    private String fromEmail;
@Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(Mail mail) throws Exception {
    	 MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    	try {
    		 
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
 
            mimeMessageHelper.setSubject(mail.getSubject());
            mimeMessageHelper.setFrom("fromEmail");
            mimeMessageHelper.setTo(mail.getToEmail());
            mimeMessageHelper.setText(mail.getContent());
 
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
 
        } catch (MessagingException e) {
            e.printStackTrace();
        } 
    }
}