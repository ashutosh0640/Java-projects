package com.luxora.ServiceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.luxora.entity.User;
import com.luxora.repository.UserRepository;
import com.luxora.service.UserService;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	private UserRepository repo;
	
	
	//Find user
	public Optional<User> findById(Integer id) {
		return repo.findById(id);
	}
	
	public List<User> findAll() {
		return repo.findAll();
	}
	
	public List<User> findAllById(List<Integer> Ids) {
		return repo.findAllById(Ids);
	}
	
	public Page<User> findAll(int page, int size) {
		page = (page<0)?0:page;
		size = (size<=0)?10:size;
		PageRequest pagable = PageRequest.of(page, size);
		return repo.findAll(pagable);
	}
	
	
	//Save user
	public User save(User user) {
		return repo.save(user);
	}
	
	public List<User> saveAll(List<User> users) {
		return repo.saveAll(users);
	}
	
	
	//Delete user
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}
	
	public void delete(User user) {
		repo.delete(user);
	}
	
	public void deleteAllById(List<Integer> ids) {
		repo.deleteAllById(ids);
	}
	
	public void deleteAll(List<User> users) {
		repo.deleteAll(users);
	}
	
	public void deleteAll() {
		repo.deleteAll();
	}
	
	
	//Find by email and number
	public User getUserByEmail(String email) {
		return repo.getUserByEmail(email);
	}
	
	public User getUserByMobileNumber(String mobile) {
		return repo.getUserByMobileNumber(mobile);
	}
	
}
