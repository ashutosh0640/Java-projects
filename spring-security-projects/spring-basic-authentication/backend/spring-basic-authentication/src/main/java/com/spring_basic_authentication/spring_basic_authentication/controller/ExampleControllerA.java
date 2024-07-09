package com.spring_basic_authentication.spring_basic_authentication.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ExampleControllerA {
	
	@GetMapping("/names")
	public List<String> getAllNames() {
		
		List<String> names = new ArrayList<>();
		names.add("Akash Kumar");
		names.add("Banny Kumari");
		names.add("Ramprashad Mishra");
		names.add("Rohit Sharma");
		names.add("Niraj Chopra");
		names.add("Marry Com");
		return names;
	}
	
	
	@GetMapping("/login")
	public void login(@RequestParam String username, @RequestParam String password ) {
		
	}
	
	

}
