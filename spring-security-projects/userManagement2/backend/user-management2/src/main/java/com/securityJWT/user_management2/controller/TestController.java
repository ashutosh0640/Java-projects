package com.securityJWT.user_management2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@GetMapping("/helloworld")
	public String helloWorld() {
		return "hello world";
	}
	
//	@GetMapping("/csrf-token")
//	public CsrfToken getCsrfToken(HttpServletRequest request) {
//		System.out.println(request.toString());
//		return (CsrfToken) request.getAttribute("_csrf");
//	}
	
//	@GetMapping("/csrf-token")
//	public CsrfToken getCsrfToken(HttpServletRequest request) {
//	    CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
//	    System.out.println(csrfToken);
//	    if (csrfToken == null) {
//	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CSRF token not found");
//	    }
//
//	    return csrfToken;
//	}

}
