package com.example.useremailservice.controller;


import com.example.useremailservice.dto.core.ResponseObject;
import com.example.useremailservice.expection.MailNotFound;
import com.example.useremailservice.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.FileNotFoundException;

@AllArgsConstructor
@RestController
@RequestMapping("/email")
public class EmailController {



  private EmailService emailService;


    @PostMapping(path = "/simple-email")
    public ResponseObject<?> sendSimpleEmail(@RequestParam String to,
                                             @RequestParam String subject,
                                             @RequestParam String message,
                                             @RequestParam String cc,
                                             @RequestParam String bcc
                                                  ) throws MessagingException {
        emailService.sendSimpleEmail(to,subject,message,cc,bcc);

        return (ResponseObject) ResponseObject
                .success()
                .title("Please check your inbox");
    }


    @PosttMapping(path = "/email-attach")
    public ResponseObject<?>  sendEmailAttachment(@RequestBody String email,
                                       @RequestBody String subject,
                                       @RequestBody String message,
                                       @RequestBody String cc,
                                       @RequestBody String bcc
                                       ) throws MailNotFound, FileNotFoundException, MessagingException {
            emailService.sendEmailWithAttachment(email, subject,message,
                    cc,bcc,null);

        return (ResponseObject) ResponseObject
                .success()
                .title("Please check your inbox");
    }


}











