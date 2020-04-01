package com.bioprac.controller.user;

import com.bioprac.security.JwtAuthenticationResponse;
import com.bioprac.security.JwtTokenProvider;
import com.bioprac.util.BiopracResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.bioprac.model.user.User;
import com.bioprac.repository.user.UserRepository;

import javax.validation.Valid;
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

		String username = user.getUsername();
		String password = user.getPassword();

		user.setRole("ROLE_USER");

		return getResponseEntity(username, password, authenticationManager, tokenProvider);

	}

	public static ResponseEntity<?> getResponseEntity(String username, String password, AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(username, password)
		);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.generateToken(authentication);

		BiopracResponse biopracResponse = new BiopracResponse(true, "User logged in successfully", new JwtAuthenticationResponse(jwt));

		return ResponseEntity.ok(biopracResponse);
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
