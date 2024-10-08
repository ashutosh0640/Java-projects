package com.spring_basic_authentication.spring_basic_authentication.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="http://localhost:5173")
@RestController
public class BasicAuthenticationController {
	
	@GetMapping("/public/1")
	public String publicAPI() {
		return "Public read api 1";
	}
	
	@GetMapping("/private/read/1")
	public String privateReadAPI() {
		return "Private read api 1";
	}
	
	@PostMapping("/private/write/2")
	public String privateWriteAPI(@RequestBody String word) {
		return "Private write api 2: "+word;
	}
	
	@PutMapping("/private/update/3")
	public String privateUpdateAPI() {
		return "Private update api 3";
	}
	
	@DeleteMapping("/private/delete/4")
	public String privateDeleteAPI() {
		return "Private delete api 4";
	}
	
	
	
	
}
