package com.bioprac.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bioprac.model.user.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	public Role findByName(String name);
}
