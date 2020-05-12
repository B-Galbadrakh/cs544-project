package cs544.group1.project.controller;

import cs544.group1.project.domain.User;
import cs544.group1.project.repo.UserRepo;
import cs544.group1.project.service.UserService;
import cs544.group1.project.service.response.UserResponse;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public void createUser(@RequestBody User user) {
    	userService.save(user);
    }
    
    @GetMapping()
    public List<UserResponse> getUsers(){
    	return userService.findAll();
    }
    
    @GetMapping("/{userid}")
    public UserResponse getUserById(@PathVariable int userid) {
    	return userService.findUserResponseById(userid);
    }
    
    @PostMapping("/{userid}")
    public User updateById(@PathVariable int userid, @RequestBody User user) {
    	return userService.update(userid, user);
    }
    
    @DeleteMapping("/{userid}")
    public void deleteUser(@PathVariable int userid) {
    	userService.delete(userid);
    }
    
    
}
