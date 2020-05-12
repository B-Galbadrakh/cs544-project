package cs544.group1.project.service.impl;

import cs544.group1.project.domain.User;
import cs544.group1.project.domain.UserRole;
import cs544.group1.project.dto.UserDTO;
import cs544.group1.project.repo.UserRepository;
import cs544.group1.project.service.UserService;
import cs544.group1.project.util.CustomError;
import cs544.group1.project.util.CustomObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

@Transactional(propagation = Propagation.REQUIRES_NEW)
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	CustomObjectMapper objectMapper;

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
					authorities.add(new SimpleGrantedAuthority(role.getUserRoles().name()));
				}
			}
			return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
		}
	}

	public User getCurrentLoggedInUser()
	{
		String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		return userRepository.findByEmail(email).get(0);
	}

	public void save(UserDTO userDTO) throws CustomError {
		List<User> tempUser = userRepository.findByEmail(userDTO.getEmail());
		if(tempUser != null && tempUser.size() > 0)
		{
			throw new CustomError(400,"Email already used by another user",null);
		}
		User user = objectMapper.getUserEntityFromDTO(userDTO);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	public List<UserDTO> findAll() {
		List<User> users = userRepository.findAll();
		List<UserDTO> collect = users.stream().map(objectMapper::getUserDTOFromEntity).collect(Collectors.toList());
		return collect;
	}

	public UserDTO findById(int userid) {
		Optional<User> user = userRepository.findById(userid);
		return user.map(objectMapper::getUserDTOFromEntity).get();
	}

	public UserDTO update(int userId, String password) {
		Optional<User> user = userRepository.findById(userId);
		User oldUser = user.get();
		if(oldUser == null){
			return null;
		}
		oldUser.setPassword(password);
		return objectMapper.getUserDTOFromEntity(userRepository.save(oldUser));
	}

	public void delete(int userId) {
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()){
			userRepository.deleteById(userId);
		}
	}
}
