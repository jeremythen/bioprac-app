package com.bioprac.controller.user;

import com.bioprac.security.JwtTokenProvider;
import com.bioprac.service.UserService;
import com.bioprac.util.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bioprac.model.user.User;
import com.bioprac.repository.user.UserRepository;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	JwtTokenProvider tokenProvider;

	@Autowired
	UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping()
	public Iterable<User> getUsers() {
		return userRepository.findAll();
	}

	@PostMapping
	public ResponseEntity<?> createUser(@Valid @RequestBody User user) {

		return userService.save(user);

	}

	@PutMapping()
	public ResponseEntity.HeadersBuilder updateUser(@Valid @RequestBody User user) {
		userRepository.save(user);
		return ResponseEntity.noContent();
	}

	@GetMapping("/{userName}")
	public ResponseEntity getUserByUserName(@PathVariable String userName) {

		User user = userRepository.findByUsername(userName);

		if(Objects.isNull(user)) {
			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("message", "No user with username " + userName + " was found");
			ResponseEntity.badRequest().body(responseMap);
		}

		Map<String, Object> userInfo = userService.getFilteredUser(user);

		return ResponseEntity.ok(userInfo);

	}

	@DeleteMapping("/{userName}")
	public ResponseEntity.HeadersBuilder deleteUserByUserName(@PathVariable String userName) {

		User user = userRepository.findByUsername(userName);

		userRepository.delete(user);

		return ResponseEntity.noContent();

	}

	@PostMapping("/{userName}/role")
	public ResponseEntity addUserRole(@PathVariable String userName, @RequestBody Map<String, String> requestMap) {

		String newRole = requestMap.get("role");

		logger.info("New role: " + newRole);

		boolean isValidRole = Stream.of(Roles.values()).anyMatch(role -> role.toString().equals(newRole));

		logger.info("isValidRole: " + isValidRole);

		logger.info("Roles.values: " + Arrays.toString(Roles.values()));

		if(!isValidRole) {
			logger.info("!isValidRole: " + isValidRole);
			Map<String, Object> responseBody = new HashMap<>();

			responseBody.put("message", "Invalid role.");
			responseBody.put("rolesAllowed", Roles.values());

			return ResponseEntity.badRequest().body(responseBody);

		}

		User user = userRepository.findByUsername(userName);

		user.setRole(newRole);

		userRepository.save(user);

		return ResponseEntity.noContent().build();

	}

	@DeleteMapping("/{userName}/role/{roleName}")
	public ResponseEntity.HeadersBuilder removeUserRole(@PathVariable String userName, @PathVariable String roleName) {

		User user = userRepository.findByUsername(userName);

		userRepository.save(user);

		return ResponseEntity.noContent();

	}

}
