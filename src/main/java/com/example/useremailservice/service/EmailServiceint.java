package com.example.useremailservice.service;

import com.example.useremailservice.expection.MailNotFound;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.FileNotFoundException;
import java.util.List;

public interface EmailServiceint {


    void sendEmailWithAttachment(String to, String subject, String message, String cc, String bcc, String attachment ) throws MailNotFound, MessagingException, FileNotFoundException;

    void sendSimpleEmail( String to, String subject, String message, String cc, String bcc) throws AddressException, MessagingException;
}
