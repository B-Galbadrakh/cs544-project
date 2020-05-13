package cs544.group1.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {
	
//	*/10 * * * * * Every ten second
//	0 * * * * * Every minute
	
	@Autowired
	EmailService emailService;
	
	@Value("${customParameter}")
	private String scheduleParameter;
	
	
	@Scheduled(cron="${customParameter}")
	public void sendEmailToComingAppointment() {
		
		String from = "tmcheckersystem@gmail.com";
		String to = "b.galbadrah12@gmail.com";
		String message = "You have a appointment in next 24 hours at McLaughlin room";
		String subject = "TM Checking appointment";
		
//		emailService.sendMail(from, to, subject, message);
		System.out.println("Email has sent");
		
	}	
}
