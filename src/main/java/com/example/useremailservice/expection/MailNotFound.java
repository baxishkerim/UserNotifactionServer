package com.example.useremailservice.expection;
import javax.mail.MessagingException;


public class MailNotFound extends MessagingException {
    public MailNotFound(String s) {
        super(s);
    }
}

