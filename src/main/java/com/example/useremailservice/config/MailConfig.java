//package com.example.useremailservice.config;
//
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
//@Configuration
//public class MailConfig {
//
//    @Value("${spring.mail.host}")
//    private  String host;
//
//    @Value("${spring.mail.port}")
//    private String username;
//
//    @Value("${spring.mail.password}")
//    private String password;
//
//    @Value("${spring.mail.port}")
//    private int port;
//
//    @Value("${spring.mail.protocol}")
//    private String protocol;
//
//
//
////
////    @Bean
////    JavaMailSender getMailSender(){
////        JavaMailSenderImpl sender  = new JavaMailSenderImpl();
////
////        sender.setHost(host);
////        sender.setUsername(username);
////        sender.setPassword(password);
////        sender.setPort(port);
////        sender.setProtocol(protocol);
////
////
////        return sender;
////
////
//
//    }
//}
