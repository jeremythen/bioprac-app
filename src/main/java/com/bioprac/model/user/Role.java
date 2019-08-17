package com.bioprac.model.user;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "role_id")
	private int id;
	
	@Column(length = 20)
	private String name;
	
	@ManyToMany(mappedBy = "roles")
	private Set<User> users;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void Name(String role) {
		this.name = role;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}
	
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	@Override
	public String toString() {
		
		final String roleDescription = String.format("{ id: %s, name: %s, users: %s }", id, name, users);
		
		return roleDescription;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		
		if(o instanceof Role) {
			Role otherRole = (Role) o;
			return this.name.equals(otherRole.name);
		}
		
		return false;
		
	}
	
}
