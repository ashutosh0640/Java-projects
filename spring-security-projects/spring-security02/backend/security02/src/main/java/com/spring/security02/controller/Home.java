package com.spring.security02.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {
	
	
	@GetMapping("/home/{home}")
	public String home(@PathVariable String home) {
		return "This is home of: "+home;
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/user/{user}")
	public String user(@PathVariable String user) {
		return "The user name is : "+user;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin/{admin}")
	public String admin(@PathVariable String admin) {
		return "The admin name is: "+admin;
	}
	
	
	

}
