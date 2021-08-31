package com.ynon.coupons.services;
import com.ynon.coupons.beans.javabeans.EmailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class EmailService {

    @Autowired
    public JavaMailSender emailSender;

    public void sendEmail( EmailMessage emailMessage ) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo( emailMessage.getToName() + "<" +  emailMessage.getToEmail() + ">");
        message.setSubject( emailMessage.getSubject() );
        message.setText( emailMessage.getMessage() );

        log.info("Sending email message: {}",emailMessage);

//        emailSender.send(message);
    }

}