package com.bioprac.controller.user;

import com.bioprac.security.JwtTokenProvider;
import com.bioprac.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import com.bioprac.model.user.User;
import com.bioprac.repository.user.UserRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	JwtTokenProvider tokenProvider;

	@Autowired
	UserService userService;
	
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
	public ResponseEntity.HeadersBuilder addUserRole(@PathVariable String userName, @RequestBody Map<String, String> requestMap) {

		User user = userRepository.findByUsername(userName);

		userRepository.save(user);

		return ResponseEntity.noContent();

	}

	@DeleteMapping("/{userName}/role/{roleName}")
	public ResponseEntity.HeadersBuilder removeUserRole(@PathVariable String userName, @PathVariable String roleName) {

		User user = userRepository.findByUsername(userName);

		userRepository.save(user);

		return ResponseEntity.noContent();

	}

}
