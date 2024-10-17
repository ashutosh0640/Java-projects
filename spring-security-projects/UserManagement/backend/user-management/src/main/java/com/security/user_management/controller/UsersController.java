package com.security.user_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.security.user_management.entity.Users;
import com.security.user_management.service.UserServiceImpl;

@RestController
public class UsersController {
	
	private final UserServiceImpl userService;

	@Autowired
	public UsersController(UserServiceImpl userService) {
		this.userService = userService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<Users> saveUser(@RequestBody Users users) {
		System.out.println(users.toString());
		try {
	        Users newUser = userService.saveUsers(users);
	        return new ResponseEntity<>(newUser, HttpStatus.OK);
	    } catch (Exception e) {
	        e.printStackTrace(); 
	        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }
	}
	
	@GetMapping("/test-get")
	public String testGet() {
		return "this is test for get";
	}
	
	@PostMapping("/test-post")
	public String testPost() {
		return "this is test for post";
	}
	
	
	@GetMapping("/user/findAll")
	public ResponseEntity<List<Users>> findAllUsers() {
		System.out.println("find all users in controller.");
		List<Users> userList = userService.findAllUsers();
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}
	
	@PutMapping("/user/resetPassword")
	public ResponseEntity<String> resetPassword(@RequestParam String oldPassword, @RequestParam String newPassword ) {
		String res = userService.resetPasswordByUser(oldPassword, newPassword);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@PutMapping("/admin/resetPassword")
	public ResponseEntity<String> resetPassword(@RequestParam long id, @RequestParam String oldPassword, @RequestParam String newPassword ) {
		String res = userService.resetPasswordByAdmin(id, oldPassword, newPassword);
		return new ResponseEntity<>(res, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/admin/deleteById")
	public ResponseEntity<String> deleteUserById(@RequestParam long id) {
		userService.deleteUserById(id);
		return new ResponseEntity<>("deleted sucessfully.", HttpStatus.OK);
	}

}
