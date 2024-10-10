package com.spring_security.jdbc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.spring_security.jdbc.repository.UsersRepository;


@Service
public class UsersServiceImpl  {
	
	private UsersRepository repo;

	@Autowired
	public UsersServiceImpl(UsersRepository repo) {
		this.repo = repo;
	}
	
}
