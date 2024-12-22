package com.shop.luxora.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shop.luxora.entity.User;
import com.shop.luxora.service.UserService;

@RestController
public class RegisterAndLogin {
	
	private UserService userService;

	public RegisterAndLogin(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody User user) {
		try {
			User saveUser = userService.save(user);
			return ResponseEntity.ok(saveUser);
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
		
	}

}
