package com.bioprac.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bioprac.model.user.Role;
import com.bioprac.model.user.User;
import com.bioprac.repository.user.RoleRepository;
import com.bioprac.repository.user.UserRepository;
import com.bioprac.util.Roles;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		Role userRole = roleRepository.findByName(Roles.USER.name());
		Set<Role> userRoles = Collections.singleton(userRole);
		user.setRoles(userRoles);
		userRepository.save(user);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
