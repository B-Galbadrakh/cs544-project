package cs544.group1.project.service;

import cs544.group1.project.domain.User;
import cs544.group1.project.dto.UserDTO;
import cs544.group1.project.util.CustomError;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService{

    UserDTO save(UserDTO user) throws CustomError;
    List<UserDTO> findAll();
    UserDTO update(int userId,UserDTO user) throws CustomError;
    void delete(int userId);
    UserDTO findById(int userId);
    public List<UserDTO> convertEntityListToResponse(List<User> userList);
}
