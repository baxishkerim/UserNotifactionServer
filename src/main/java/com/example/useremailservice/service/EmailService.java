package com.example.useremailservice.service;


import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileNotFoundException;


@Service
public class EmailService  implements  EmailServiceint  {


    public JavaMailSender sender;



    public void sendEmailWithAttachment(String to, String subject, String text, String cc, String bcc, String attachment) throws MessagingException, FileNotFoundException {
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setTo(InternetAddress.parse("email1@test.com,email2@test.com"));
        messageHelper.setSubject(subject);
        messageHelper.setText(text);
        messageHelper.setCc(InternetAddress.parse("email1@test.com,email2@test.com"));
        messageHelper.setBcc(InternetAddress.parse("email1@test.com,email2@test.com"));

        FileSystemResource file = new FileSystemResource(ResourceUtils.getFile(attachment));
        messageHelper.addAttachment("Purchase Order", file);

        sender.send(mimeMessage);
    }


    public void sendSimpleEmail(String to, String subject,String message,String cc , String bcc) throws MessagingException {

        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setTo(InternetAddress.parse("email1@test.com,email2@test.com"));
        messageHelper.setSubject(subject);
        messageHelper.setText(message);
        messageHelper.setCc(InternetAddress.parse("email1@test.com,email2@test.com"));
        messageHelper.setBcc(InternetAddress.parse("email1@test.com,email2@test.com"));


        sender.send(mimeMessage);
    }

}


//    //TODO add exception handler based on ResponseObject
//            //TODO get 'from' from properties file
//            //TODO get these parameters from request








