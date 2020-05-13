package cs544.group1.project.testcases;


import cs544.group1.project.domain.User;
import cs544.group1.project.domain.UserRoles;
import cs544.group1.project.dto.UserDTO;
import cs544.group1.project.repo.UserRepository;
import cs544.group1.project.service.UserService;
import cs544.group1.project.service.impl.UserServiceImpl;
import cs544.group1.project.util.CustomError;
import cs544.group1.project.util.CustomObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.isA;

@SpringBootTest
public class UserTestCases {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JavaMailSender javaMailSender;

	@InjectMocks
	private UserServiceImpl mockUsrService;

	@Mock
	private UserRepository mockedUserRepository;

	@Mock
	private PasswordEncoder mockedPasswordEncoder;

	@Mock
	private CustomObjectMapper mockObjectMapper;

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

	@Test
	public void testUserCreation() {
		List<User> tempUsers = new ArrayList<>();
		tempUsers.add(new User());
		Mockito.when(mockedUserRepository.findByEmail(isA(String.class))).thenReturn(tempUsers);
		try
		{
			userService.save(getSampleDTO());
			Assert.isTrue(false,"email validation failed");
		}
		catch(CustomError exc)
		{
			Assert.isTrue(exc.getCode().equals(400) && exc.getMessage().contains("Email already used by another user"),"email validation successful");
		}
		catch(Exception exc)
		{
			Assert.isTrue(false,"email validation failed");
		}
	}

	public UserDTO getSampleDTO()
	{
		UserDTO userDTO = new UserDTO();
		userDTO.setEmail("d@gm.cm");
		userDTO.setFirstName("Muyinza");
		userDTO.setLastName("Daniel");
		userDTO.setPassword("12345");
		userDTO.setRoles(new UserRoles[]{UserRoles.ADMIN});
		userDTO.setGender('M');
		return userDTO;
	}

}
