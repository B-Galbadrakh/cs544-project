package cs544.group1.project.controller;

import cs544.group1.project.domain.User;
import cs544.group1.project.dto.LogInRequest;
import cs544.group1.project.dto.LogInResponse;
import cs544.group1.project.dto.UserDTO;
import cs544.group1.project.service.UserService;
import cs544.group1.project.util.CustomError;
import cs544.group1.project.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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
@Api(value = "Users of TM Checking", description = "Operations pertaining to user in Users of TM Checking")
public class UserController extends ProjectDefaultController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@PostMapping()
	@ApiOperation(value = "Create new user", response = UserDTO.class)
	public UserDTO createUser(@Valid @RequestBody UserDTO user) throws CustomError {
		return userService.save(user);
	}

	@PostMapping("/login")
	@ApiOperation(value = "User login")
	public ResponseEntity<?> login(@Valid @RequestBody LogInRequest req) {
		try {
			authenticate(req.getUsername(), req.getPassword());
		} catch (Exception exc) {
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
	@ApiOperation(value = "View a list of available users", response = UserDTO.class)
	public List<UserDTO> getUsers() {
		return userService.findAll();
	}

	@GetMapping("/{userid}")
	@ApiOperation(value = "View user using ID", response = UserDTO.class)
	public UserDTO getUserById(@PathVariable int userid) {
		return userService.findById(userid);
	}

	@PutMapping("/{userid}")
	@ApiOperation(value = "Update user using ID", response = UserDTO.class)
	public UserDTO updateById(@PathVariable int userid, @RequestBody UserDTO user) throws CustomError {
		return userService.update(userid, user);
	}

	@DeleteMapping("/{userid}")
	@ApiOperation(value = "Delete user using ID", response = void.class)
	public void deleteUser(@PathVariable int userid) {
		userService.delete(userid);
	}

}