package com.bioprac.service;

import com.bioprac.model.user.User;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public interface UserService {

    ResponseEntity save(User user);

    User findByUsername(String username);

    Map<String, Object> getFilteredUser(User user);

}
