package com.bioprac.controller.Auth;

import com.bioprac.model.user.Role;
import com.bioprac.model.user.User;
import com.bioprac.repository.user.RoleRepository;
import com.bioprac.repository.user.UserRepository;
import com.bioprac.security.JwtAuthenticationResponse;
import com.bioprac.security.JwtTokenProvider;
import com.bioprac.security.UserService;
import com.bioprac.util.BiopracResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RoleRepository roleRepository;

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

        Set<Role> roles = new HashSet<>();

        roles.add(new Role("USER"));

        user.setRoles(roles);

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

}
