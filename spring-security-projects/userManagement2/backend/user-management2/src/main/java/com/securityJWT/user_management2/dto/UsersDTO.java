package com.securityJWT.user_management2.dto;

import java.util.HashSet;
import java.util.Set;

import com.securityJWT.user_management2.entity.Role;


public class UsersDTO {
	
	private long userId;
	
	private String username;
	
	private String password;
	
	private Set<Role> role = new HashSet();

	public UsersDTO() {}

	public UsersDTO(long userId, String username, Set<Role> role) {
		super();
		this.userId = userId;
		this.username = username;
		this.role = role;
	}

	public UsersDTO(long userId, String username, String password, Set<Role> role) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UsersDTO [userId=" + userId + ", username=" + username + ", password=" + password + ", role=" + role
				+ "]";
	}
	
}
