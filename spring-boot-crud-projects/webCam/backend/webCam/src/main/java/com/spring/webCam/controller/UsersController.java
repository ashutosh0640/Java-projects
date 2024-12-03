package com.spring.webCam.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.webCam.entity.Users;
import com.spring.webCam.service.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {
	
	private UsersService userService;

	public UsersController(UsersService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/saveAll")
	public List<Users> saveAll(Iterable<Users> users) {
		return userService.saveAll(null);
	}

	@GetMapping("/findAll")
	public List<Users> findAll() {
		return userService.findAll();
	}

	@GetMapping("/findAllById")
	public List<Users> findAllById(Iterable<Long> ids) {
		return userService.findAllById(ids);
	}

	@PostMapping("/save")
	public Users save(Users user) {
		return userService.save(user);
	}

	@GetMapping("/findById")
	public Users findById(Long id) {
		return userService.findById(id);
	}

	@GetMapping("/exist")
	public boolean existsById(Long id) {
		return userService.existsById(id);
	}

	@GetMapping("/count")
	public long count() {
		return userService.count();
	}

	@DeleteMapping("/deleteById")
	public void deleteById(Long id) {
		userService.deleteById(id);
	}

	@DeleteMapping("/delete")
	public void delete(Users user) {
		userService.delete(user);
	}

	@DeleteMapping("/deleteAllByIds")
	public void deleteAllById(Iterable<Long> ids) {
		userService.deleteAllById(ids);
	}

	@DeleteMapping("/deleteAll")
	public void deleteAll(Iterable<Users> users) {
		userService.deleteAll(users);
		
	}

	@DeleteMapping("/deleteAllUsers")
	public void deleteAll() {
		userService.deleteAll();
	}
	
	

}
