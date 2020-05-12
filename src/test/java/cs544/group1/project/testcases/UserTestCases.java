package cs544.group1.project.testcases;


import cs544.group1.project.domain.User;
import cs544.group1.project.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
public class UserTestCases {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Test
	void contextLoads() {
		User user = new User();
		user.setFirstName("TestUser");
		user.setLastName("Test Lastname");
		System.out.println(userService);
		//assertNotNull(userService.save(user));
	}
	
	@Test
	void mailSender() {
	   User user = new User();
	   user.setFirstName("TestUser");
	   user.setLastName("Test Lastname");
	   System.out.println(userService);
	   SimpleMailMessage msg = new SimpleMailMessage();
	   msg.setTo("tmcheckersystem@gmail.com", "tmcheckersystem@gmail.com");
	   msg.setSubject("Testing from Spring Boot");
	   msg.setText("Hello World \n Spring Boot Email");
	   javaMailSender.send(msg);
	}

}
