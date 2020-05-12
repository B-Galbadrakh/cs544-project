package cs544.group1.project.controller;

import cs544.group1.project.contract.LogInRequest;
import cs544.group1.project.contract.LogInResponse;
import cs544.group1.project.domain.User;
import cs544.group1.project.service.UserService;

import java.util.List;
import java.util.Map;

import cs544.group1.project.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping()
    public void createUser(@RequestBody User user) {
        userService.save(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LogInRequest req){
        try
        {
            authenticate(req.getUsername(), req.getPassword());
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
            return ResponseEntity.status(401).body("Invalid credentials");
        }
        final UserDetails userDetails = userService.loadUserByUsername(req.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new LogInResponse(token));
    }

    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
    
    @GetMapping()
    public List<User> getUsers(){
    	return userService.findAll();
    }
    
    @GetMapping("/{userid}")
    public User getUserById(@PathVariable int userid) {
    	return userService.findById(userid);
    }
    
    @PostMapping("/{userid}")
    public User updateById(@PathVariable int userid, @RequestBody Map<String, String>password) {
    	return userService.update(userid, password.get("password"));
    }
    
    @DeleteMapping("/{userid}")
    public void deleteUser(@PathVariable int userid) {
        userService.delete(userid);
    }
    
    
}
