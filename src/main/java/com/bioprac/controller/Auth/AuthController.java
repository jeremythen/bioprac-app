package com.bioprac.controller.Auth;

import com.bioprac.model.user.User;
import com.bioprac.repository.user.UserRepository;
import com.bioprac.security.JwtAuthenticationResponse;
import com.bioprac.security.JwtTokenProvider;
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
import java.util.HashMap;
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


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, Object> userMap) {

        final String username = (String) userMap.get("username");
        final String password = (String) userMap.get("password");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));

    }

}
