package com.bioprac.model.user;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "role")
public class Role {

	public Role() {
		this.name = "USER";
	}
	public Role(String name) {
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "role_id")
	private int id;
	
	@Column(length = 20)
	private String name;

	@JsonIgnore
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

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		
		final String roleDescription = String.format("{ id: %s, name: %s }", id, name);
		
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
