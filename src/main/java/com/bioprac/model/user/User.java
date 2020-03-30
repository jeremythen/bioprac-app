package com.bioprac.model.user;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "user")
//@NamedQuery(name = "User.findAllUsers", query = "SELECT u.username, u.id, u.email FROM User u")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int id;
	
	@NotBlank
	@Column(length = 30, nullable = false)
	private String name;
	
	@Column(name = "last_name", length = 30)
	private String lastName;
	
	@NotBlank
	@Column(name = "username", length = 30, nullable = false, unique = true)
	private String username;
	
	@NotBlank
	@Column(length = 50, nullable = false, unique = true)
	@Email
	private String email;
	
	//@JsonIgnore
	@NotBlank
	@Size(min = 6, max = 250)
	@Column(nullable = false)
	private String password;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
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
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		final String userDescription = String.format
					("{id: %s, username: %s, name: %s, lastName: %s, email: %s}",
					id, username, name, lastName, email);
		
		return userDescription;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, username);
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof User) {
			User otherUser = (User) o;
			return this.email.equals(otherUser.email) && this.username.equals(otherUser.username);
		}
		return false;
	}
	
}
