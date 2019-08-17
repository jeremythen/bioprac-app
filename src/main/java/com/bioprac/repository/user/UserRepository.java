package com.bioprac.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bioprac.model.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUsername(String username);
	
	public boolean existsByEmail(String email);
	
	public boolean existsByUsername(String username);
	
	public Iterable<User> findAllUsers();
	
}


