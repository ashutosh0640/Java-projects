package com.spring_security.jdbc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring_security.jdbc.entity.User;
import com.spring_security.jdbc.repository.UsersRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	
	private UsersRepository repo;
	
	@Autowired
	public UserDetailsServiceImpl(UsersRepository repo) {
		this.repo = repo;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user = repo.findUsersByUserName(username)
//				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: "+username));
		return null;
	}

}
