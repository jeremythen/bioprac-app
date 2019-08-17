package com.bioprac.security;

import com.bioprac.model.user.User;

public interface UserService {
	
	void save(User user);

    User findByUsername(String username);

}
