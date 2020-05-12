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

public interface UserService extends UserDetailsService{
    User save(User user);
    List<User> findAll();
    User update(int userId, String password);
    void delete(int userId);
    User findById(int userId);
}
