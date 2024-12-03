package com.spring.webCam.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.spring.webCam.entity.Users;
import com.spring.webCam.repository.UserRepository;

@Service
public class UsersService {
	
	private UserRepository userRepo;

	public UsersService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	
	public <S extends Users> List<S> saveAll(Iterable<S> users) {
		return userRepo.saveAll(users);
	}

	public List<Users> findAll() {
		return userRepo.findAll();
	}

	public List<Users> findAllById(Iterable<Long> ids) {
		return userRepo.findAllById(ids);
	}

	public <S extends Users> S save(S User) {
		return userRepo.save(User);
	}

	public Users findById(Long id) {
		return userRepo.findById(id)
				.orElseThrow(()->new RuntimeException("User Not Found."));
	}

	public boolean existsById(Long id) {
		return userRepo.existsById(id);
	}

	public long count() {
		return userRepo.count();
	}

	public void deleteById(Long id) {
		userRepo.deleteById(id);
	}

	public void delete(Users user) {
		userRepo.delete(user);
		
	}

	public void deleteAllById(Iterable<? extends Long> ids) {
		userRepo.deleteAllById(ids);
	}

	
	public void deleteAll(Iterable<? extends Users> users) {
		userRepo.deleteAll(users);
	}

	public void deleteAll() {
		userRepo.deleteAll();
	}
	
	

}
