package cs544.group1.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cs544.group1.project.domain.User;
import cs544.group1.project.repo.UserRepo;
import cs544.group1.project.service.response.UserResponse;

@Component
public class UserService {
	
	@Autowired
	UserRepo userRepository;
	
	public void save(User user) {
		
		userRepository.save(user);
	}
	
	public List<UserResponse> findAll(){
		List<User> users = userRepository.findAll();
		List<UserResponse> userResponses = new ArrayList<>();
		for(User u: users) {
			UserResponse userResponse = new UserResponse();
			userResponse.setCreatedDate(u.getCreatedDate());
			userResponse.setEmail(u.getEmail());
			userResponse.setFirstName(u.getFirstName());
			userResponse.setGender(u.getGender());
			userResponse.setId(u.getId());
			userResponse.setLastName(u.getLastName());
			userResponse.setPassword(u.getPassword());
			userResponse.setUpdatedDate(u.getUpdatedDate());
			userResponses.add(userResponse);
		}
		return userResponses;
	}
	
	public UserResponse findUserResponseById(int userid) {
		Optional<User> user = userRepository.findById(userid);
		UserResponse userResponse = new UserResponse();
		if(user.isPresent()) {
			userResponse.setCreatedDate(user.get().getCreatedDate());
			userResponse.setEmail(user.get().getEmail());
			userResponse.setFirstName(user.get().getFirstName());
			userResponse.setGender(user.get().getGender());
			userResponse.setId(user.get().getId());
			userResponse.setLastName(user.get().getLastName());
			userResponse.setPassword(user.get().getPassword());
			userResponse.setUpdatedDate(user.get().getUpdatedDate());
		 return userResponse;
		}
		else {
			return null;
		}
	}
	
	public User findById(int userid) {
		Optional<User> user = userRepository.findById(userid);
		return user.isPresent() ? user.get() : null;
	}
	
	public User update(int userId, User user) {
		User oldUser = findById(userId);
    	if(oldUser == null){
    		return null;
    	}
    	oldUser.setPassword(user.getPassword());
    	oldUser.setEmail(user.getEmail());
    	return userRepository.save(oldUser);
	}
	
	public void delete(int userId) {
		User oldUser = findById(userId);
    	if(oldUser == null){
    		return;
    	}
		userRepository.deleteById(userId);
	}

}
