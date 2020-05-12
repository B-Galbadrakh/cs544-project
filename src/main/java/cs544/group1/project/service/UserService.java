package cs544.group1.project.service;

import cs544.group1.project.domain.User;
import cs544.group1.project.dto.UserDTO;
import cs544.group1.project.util.CustomError;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService{

    User save(UserDTO user) throws CustomError;
    List<UserDTO> findAll();
    UserDTO update(int userId, String password);
    void delete(int userId);
    UserDTO findById(int userId);
}
