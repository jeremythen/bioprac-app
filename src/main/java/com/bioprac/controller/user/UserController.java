package com.bioprac.controller.user;

import com.bioprac.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import com.bioprac.model.user.User;
import com.bioprac.repository.user.UserRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	JwtTokenProvider tokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping()
	public Iterable<User> getUsers() {
		return userRepository.findAll();
	}

	@PostMapping
	public ResponseEntity<?> createUser(@Valid @RequestBody User user) {

		Map<String, Object> responseMap = new HashMap<>();

		if(userRepository.existsByEmail(user.getEmail())) {
			responseMap.put("message", "Email is already taken.");
			return ResponseEntity.badRequest().body(responseMap);
		}

		if(userRepository.existsByUsername(user.getUsername())) {
			responseMap.put("message", "Username is already taken.");
			return ResponseEntity.badRequest().body(responseMap);
		}

		user.setRole("ROLE_USER");

		userRepository.save(user);

		return ResponseEntity.ok(user);

	}

	@PutMapping()
	public ResponseEntity.HeadersBuilder updateUser(@Valid @RequestBody User user) {
		userRepository.save(user);
		return ResponseEntity.noContent();
	}

	@GetMapping("/{userName}")
	public User getUserByUserName(@PathVariable String userName) {
		return userRepository.findByUsername(userName);
	}

	@DeleteMapping("/{userName}")
	public ResponseEntity.HeadersBuilder deleteUserByUserName(@PathVariable String userName) {

		User user = userRepository.findByUsername(userName);

		userRepository.delete(user);

		return ResponseEntity.noContent();

	}

	@PostMapping("/{userName}/role")
	public User addUserRole(@PathVariable String userName, @RequestBody Map<String, String> requestMap) {

		User user = userRepository.findByUsername(userName);

		userRepository.save(user);

		return user;

	}

	@DeleteMapping("/{userName}/role/{roleName}")
	public User removeUserRole(@PathVariable String userName, @PathVariable String roleName) {

		User user = userRepository.findByUsername(userName);

		userRepository.save(user);

		return user;

	}

}
