package cs544.group1.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {
	
	@Autowired
	EmailService emailService;
	
	
//	@Scheduled(fixedRate = 10000)
	@Scheduled(cron="0 0 0 ? * * *")		//Schedule for 00:00
	public void sendEmailToComingAppointment() {
		
		String from = "tmcheckersystem@gmail.com";
		String to = "b.galbadrah12@gmail.com";
		String message = "You have a appointment in next 24 hours at McLaughlin room";
		String subject = "TM Checking appointment";
		
		emailService.sendMail(from, to, subject, message);
		System.out.println("Email has sent");
		
	}	
}
