package com.spring_basic_authentication.spring_basic_authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring_basic_authentication.spring_basic_authentication.service.FetchUserDetails;

@CrossOrigin(origins="http://localhost:5173")
@RestController
public class BasicAuthenticationController {
	
	
	private final FetchUserDetails service;
	
	@Autowired
	public BasicAuthenticationController(FetchUserDetails service) {
		this.service = service;
	}

	@GetMapping("/public/1")
	public String publicAPI() {
		return "Public read api 1";
	}
	
	@GetMapping("/private/read/1")
	public String privateReadAPI() {
		return "Private read api 1";
	}
	
	@PostMapping("/private/write/2")
	public String privateWriteAPI() {
		return "Private write api 2: "+ service.userDetail();
	}
	
	@PutMapping("/private/update/3")
	public String privateUpdateAPI() {
		return "Private update api 3"+ service.userDetail();
	}
	
	@DeleteMapping("/private/delete/4")
	public String privateDeleteAPI() {
		return "Private delete api 4"+ service.userDetail();
	}
	
}
