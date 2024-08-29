package com.luxora.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.luxora.entity.User;

public interface UserService {
	
	
		//Find user
		public Optional<User> findById(Integer id);
		
		public List<User> findAll();
		
		public List<User> findAllById(List<Integer> Ids);
		
		public Page<User> findAll(int page, int size);
		
		
		//Save user
		public User save(User user);
		
		public List<User> saveAll(List<User> users);
		
		
		//Delete user
		public void deleteById(Integer id);
		
		public void delete(User user);
		
		public void deleteAllById(List<Integer> ids);
		
		public void deleteAll(List<User> users);
		
		public void deleteAll();
		
		
		//Find by email and number
		public User getUserByEmail(String email);
		
		public User getUserByMobileNumber(String mobile);

}
