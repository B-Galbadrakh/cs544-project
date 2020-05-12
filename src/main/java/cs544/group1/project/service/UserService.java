package cs544.group1.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import cs544.group1.project.domain.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cs544.group1.project.domain.User;
import cs544.group1.project.repo.UserRepo;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	UserRepo userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<User> users = userRepository.findByEmail(username);
		if(users == null || users.isEmpty())
		{
			throw new UsernameNotFoundException("User not found with email: " + username);
		}
		else
		{
			List<GrantedAuthority> authorities = new ArrayList<>();
			User user = users.get(0);
			authorities.add(new SimpleGrantedAuthority("ADMIN"));
			if(user.getRole() != null && !user.getRole().isEmpty())
			{
				for(UserRole role : user.getRole())
				{
					authorities.add(new SimpleGrantedAuthority(role.getRole().name()));
				}
			}
			return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
		}
	}
	
	public void save(User user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		userRepository.save(user);
	}
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(int userid) {
		Optional<User> user = userRepository.findById(userid);
		return user.isPresent() ? user.get(): null; 
	}
	
	public User update(int userId, String password) {
		User oldUser = findById(userId);
    	if(oldUser == null){
    		return null;
    	}
    	oldUser.setPassword(password);
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
