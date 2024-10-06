package com.reactSpring.fullCrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reactSpring.fullCrud.entity.User;
import com.reactSpring.fullCrud.repository.UserRepository;

@Service
public class UserService {
	
	
	@Autowired
	private UserRepository repo;
	
	
	public User saveUser(User user) {
		User savedUser = repo.save(user);
		return savedUser;
	}
	
	public List<User> findAllUser() {
		List<User> users = repo.findAll();
		return users;
	}
	
	public Optional<User> findUserById(Long id) {
		Optional<User> user = repo.findById(id);
		return user;
	}
	
	public void deleteUserById(Long id) {
		repo.deleteById(id);
	}
	
	public boolean matchUserPassword(String userId, String password) {
		String pass = repo.findPasswordByUserId(userId);
		System.out.println("original pass: "+pass+" pass: "+password+ " id: "+userId);
		return (password.equals(pass))?true:false;
	}

}
