package com.spring_security.InMemory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private InMemoryUserDetailsManager inmemoryUserDetailsManager;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@GetMapping("/name")
	public ResponseEntity<String> getUserName() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if(principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		}
		else {
			username = principal.toString();
		}
		return ResponseEntity.ok(username);
		
	}
	
	@PostMapping("/changePassword")
	public ResponseEntity<String> changePassword(@RequestParam String oldPassword, @RequestParam String newPassword) {
		Object principal  = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			UserDetails user = inmemoryUserDetailsManager.loadUserByUsername(username);
			if(passwordEncoder.matches(oldPassword, user.getPassword())) {
				inmemoryUserDetailsManager.updatePassword(user, passwordEncoder.encode(newPassword));
				return ResponseEntity.ok("password updated.");
			}else {
				return ResponseEntity.badRequest().body("Old password is incorrect.");
				
			}
		}
		return ResponseEntity.status(403).body("User not authenticated.");
	}
	
	@PostMapping("/create")
	public ResponseEntity<String> createUser(@RequestParam String username, @RequestParam String password, @RequestParam String role) {
		if(inmemoryUserDetailsManager.userExists(username)) {
			return ResponseEntity.badRequest().body(username+" is already exists.");
		}else {
			UserDetails user = User.withUsername(username).password(passwordEncoder.encode(password)).roles(role).build();
			inmemoryUserDetailsManager.createUser(user);
			return ResponseEntity.ok("User created.");
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteUser(@RequestParam String username, @RequestParam String password) {
		
		if(inmemoryUserDetailsManager.userExists(username)) {
			inmemoryUserDetailsManager.deleteUser(username);
			return ResponseEntity.ok("User deleted successfully");
		}
		return ResponseEntity.badRequest().body("User not found.");
	}
}
