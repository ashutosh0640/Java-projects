package com.InMemorySecurityProject_01.InMemorySecurityProject_01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.InMemorySecurityProject_01.InMemorySecurityProject_01.entity.Students;
import com.InMemorySecurityProject_01.InMemorySecurityProject_01.repo.StudentRepo;

@Service
public class SecurityStudentUserDetailsService implements UserDetailsService{

	
	@Autowired
	private StudentRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//return repo.findByEmail(username);
		Students user = repo.findByEmail(username);
		if (user == null) {
			throw new Exception();
		}
		
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(), user.getAuthorities()
				);
	}

}
