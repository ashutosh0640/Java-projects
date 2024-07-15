package com.spring_security.InMemory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class LoginController {
	
	
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@PostMapping("/login")
	public ResponseEntity<?> loging(@RequestBody UserLogin user) {
		System.out.println(user.getUsername()+" "+user.getPassword());
		
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
					);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
			
			return ResponseEntity.ok(new AuthResponse(user.getUsername(), "Login Successful"));
			
		}catch (AuthenticationException e) {
	
			return ResponseEntity.status(401).body(new AuthResponse(user.getUsername(), "Login failed"));
		}
		
	}
	
	@GetMapping("/logout")
	public ResponseEntity<?> logout(HttpSession session) {
		session.invalidate();
		return ResponseEntity.ok(new AuthResponse("Login Successful"));
		
	}

}

class AuthResponse {
	
	private String username;
	private String message;
//	private String token;
	
	
	
	public AuthResponse(String username, String message) {
		super();
		this.username = username;
		this.message = message;
	}

	public AuthResponse(String message) {
	super();
	this.message = message;
}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	@Override
	public String toString() {
		return "AuthResponse [username=" + username + ", message=" + message + "]";
	}
	
}


