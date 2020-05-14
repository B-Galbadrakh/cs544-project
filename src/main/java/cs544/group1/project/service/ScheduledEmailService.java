package cs544.group1.project.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import cs544.group1.project.domain.User;

@Service
public class ScheduledEmailService {
	
//	*/10 * * * * * Every ten second
//	0 * * * * * Every minute
	
	@Autowired
	EmailService emailService;
	
	@Autowired 
	ReservationService reservationService;
	
	@Value("${scheduledEmailCron}")
	private String scheduleParameter;
	
	
	@Scheduled(cron="${scheduledEmailCron}")
	public void sendEmailToComingAppointment() {
		
		LocalDate currentDate = LocalDate.now();
		
		List<User> users = reservationService.findAcceptedReservationsByDate(currentDate);
		
		for(User user : users) {
			String email = user.getEmail();
//			send(email);
		}
	}
	
	private void send(String toEmail) {
		String message = "You have a appointment in next 24 hours";
		String subject = "TM Checking appointment";
		
		emailService.sendMail(toEmail, subject, message);
		System.out.println("Email has sent");
		
	}
}
