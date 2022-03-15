package ru.forsh.sweater.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {
    private final JavaMailSender mailSenderService;

    public MailSenderService(JavaMailSender javaMailSender){
        this.mailSenderService = javaMailSender;
    }

    public void send(String emailTo, String subject, String message){
        var mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("");
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailSenderService.send(mailMessage);
    }
}
