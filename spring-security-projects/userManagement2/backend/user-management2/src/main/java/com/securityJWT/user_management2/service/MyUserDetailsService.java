package com.securityJWT.user_management2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.securityJWT.user_management2.entity.Users;
import com.securityJWT.user_management2.exceptions.UserNotFoundException;
import com.securityJWT.user_management2.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepo.findByUsername(username)
				.orElseThrow(()->new UserNotFoundException("User by name: "+username+" not found."));
		return user;
	}

}
