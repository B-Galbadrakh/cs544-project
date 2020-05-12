package cs544.group1.project.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cs544.group1.project.service.EmailService;

@RestController
public class EmailController {
	
	
	@Autowired
    private EmailService emailService;

    @GetMapping(value = "/sendmail")
    public String mailSender() {
    	
    	System.out.println("function");
        emailService.sendMail("tmcheckersystem@gmail.com", "b.galbadrah12@gmail.com", "Test Subject", "Test mail");
        System.out.println("Email sent");
        
        
        return "emailsent";
    }
}
