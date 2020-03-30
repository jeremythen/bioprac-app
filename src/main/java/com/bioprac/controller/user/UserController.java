package com.bioprac.controller.user;

import javax.validation.Valid;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bioprac.model.user.User;
import com.bioprac.repository.user.UserRepository;
import com.bioprac.security.JwtAuthenticationResponse;
import com.bioprac.security.JwtTokenProvider;
import com.bioprac.security.UserService;
import com.bioprac.util.BiopracResponse;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
    JwtTokenProvider tokenProvider;

	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody User user) {

		if(userRepository.existsByEmail(user.getEmail())) {
			return new ResponseEntity<>(new BiopracResponse(false, "Email is already taken."), BAD_REQUEST);
		}
		
		if(userRepository.existsByUsername(user.getUsername())) {
			return new ResponseEntity<>(new BiopracResponse(false, "Username is already taken."), BAD_REQUEST);
		}
		
		userService.save(user);
		
		return ResponseEntity.ok(new BiopracResponse(true, "User registered successfully"));

	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, Object> userMap) {
		
		final String username = (String) userMap.get("username");
		final String password = (String) userMap.get("password");
		
		Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        String jwt = tokenProvider.generateToken(authentication);
        
        BiopracResponse biopracResponse = new BiopracResponse(true, "User logged in successfully", new JwtAuthenticationResponse(jwt));
   
        return ResponseEntity.ok(biopracResponse);
		
	}
	
	@GetMapping("/users")
	public Iterable<User> getUsers() {
		return userRepository.findAll();
	}

}
