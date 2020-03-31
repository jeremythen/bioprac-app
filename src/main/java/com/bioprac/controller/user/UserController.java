package com.bioprac.controller.user;

import com.bioprac.model.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	
	@GetMapping()
	public Iterable<User> getUsers() {
		return userRepository.findAll();
	}

	@PostMapping
	public User createUser(@Valid @RequestBody User user) {

		return userRepository.save(user);

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

		String roleName = requestMap.get("role");

		Role role = new Role(roleName);

		user.getRoles().add(role);

		userRepository.save(user);

		return user;

	}

	@DeleteMapping("/{userName}/role/{roleName}")
	public User removeUserRole(@PathVariable String userName, @PathVariable String roleName) {

		User user = userRepository.findByUsername(userName);

		user.getRoles().remove(roleName);

		userRepository.save(user);

		return user;

	}

}
