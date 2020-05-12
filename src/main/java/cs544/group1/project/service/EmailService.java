package cs544.group1.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	public EmailService() {
	}

	public void sendMail(String from, String toEmail, String subject, String message) {
		
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(from, toEmail);
		msg.setSubject(subject);
		msg.setText(message);
		javaMailSender.send(msg);

	}
}
