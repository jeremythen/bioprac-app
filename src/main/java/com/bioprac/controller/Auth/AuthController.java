package com.bioprac.controller.Auth;

import com.bioprac.controller.user.UserController;
import com.bioprac.model.user.User;
import com.bioprac.repository.user.UserRepository;
import com.bioprac.security.JwtAuthenticationResponse;
import com.bioprac.security.JwtTokenProvider;
import com.bioprac.util.BiopracResponse;
import org.slf4j.ILoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user) {

        if(userRepository.existsByEmail(user.getEmail())) {
            return new ResponseEntity<>(new BiopracResponse(false, "Email is already taken."), BAD_REQUEST);
        }

        if(userRepository.existsByUsername(user.getUsername())) {
            return new ResponseEntity<>(new BiopracResponse(false, "Username is already taken."), BAD_REQUEST);
        }

        user.setRole("ROLE_USER");

        return ResponseEntity.ok(user);

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, Object> userMap) {

        final String username = (String) userMap.get("username");
        final String password = (String) userMap.get("password");

        return UserController.getResponseEntity(username, password, authenticationManager, tokenProvider);

    }

}
