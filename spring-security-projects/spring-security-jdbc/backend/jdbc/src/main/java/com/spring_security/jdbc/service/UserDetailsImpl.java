package com.spring_security.jdbc.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.spring_security.jdbc.entity.User;

public class UserDetailsImpl implements UserDetails{
	
	private static final long serialVersionUID  = 1L;

	public static UserDetails build(Optional<User> user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	public static UserDetails build(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
