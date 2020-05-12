package cs544.group1.project.service.impl;

import java.util.List;
import java.util.Optional;

import cs544.group1.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs544.group1.project.domain.User;
import cs544.group1.project.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public List<User> findAll(){
		return userRepository.findAll();
	}

	@Override
	public User findById(int userId) {
		Optional<User> user = userRepository.findById(userId);
		return user.orElse(null);
	}

	public User update(int userId, String password) {
		User oldUser = findById(userId);
		//TODO: Throw user not found exception and catch in a controller advice
		if(oldUser == null){
			return null;
		}
		oldUser.setPassword(password);
		return userRepository.save(oldUser);
	}

	public void delete(int userId) {
		//TODO: unnecessary block
		User oldUser = findById(userId);
		if(oldUser == null){
			return;
		}
		//
		userRepository.deleteById(userId);
	}
}
