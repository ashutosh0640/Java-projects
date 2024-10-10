package com.spring_security.jdbc.conrtoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring_security.jdbc.service.UsersService;

@RestController
public class UsersController {
	
	private UsersService service;
	
	
	@Autowired
	public UsersController(UsersService service) {
		this.service = service;
	}


	@GetMapping("/public/test")
	public String publicTtestController() {
		return "This is public api testing.";
	}
	
	
	@GetMapping("/private/test")
	public String privateTestController() {
		return "This is private api testing.";
	}

}
