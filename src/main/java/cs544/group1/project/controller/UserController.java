package cs544.group1.project.controller;

import cs544.group1.project.dto.LogInRequest;
import cs544.group1.project.dto.LogInResponse;
import cs544.group1.project.dto.UserDTO;
import cs544.group1.project.service.UserService;
import cs544.group1.project.util.CustomError;
import cs544.group1.project.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController extends ProjectDefaultController{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping()
    public void createUser(@Valid @RequestBody UserDTO user) throws CustomError {
        userService.save(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LogInRequest req){
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
    public List<UserDTO> getUsers(){
    	return userService.findAll();
    }
    
    @GetMapping("/{userid}")
    public UserDTO getUserById(@PathVariable int userid) {
    	return userService.findById(userid);
    }
    
    @PostMapping("/{userid}")
    public UserDTO updateById(@PathVariable int userid, @RequestBody Map<String, String>password) {
    	return userService.update(userid, password.get("password"));
    }
    
    @DeleteMapping("/{userid}")
    public void deleteUser(@PathVariable int userid) {
        userService.delete(userid);
    }

}
