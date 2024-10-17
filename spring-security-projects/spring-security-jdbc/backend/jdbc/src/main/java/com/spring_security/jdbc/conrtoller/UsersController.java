package com.spring_security.jdbc.conrtoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring_security.jdbc.DTO.UserDTO;
import com.spring_security.jdbc.entity.User;
import com.spring_security.jdbc.service.UserServiceImpl;

@RestController
//@RequestMapping("/api/admin")
//@PreAuthorize(value = "hasRole('ROLE_ADMIN')")
public class UsersController {
	
	private UserServiceImpl service;
	
	@Autowired
	public UsersController(UserServiceImpl service) {
		this.service = service;
	}
	
	@GetMapping("/test")
	public String getTestMethod() {
		return "this is test";
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody User user) {
		service.addUser(user);
		return new ResponseEntity<>("User registerd successfully", HttpStatus.OK);
	}


	@GetMapping("/admin/get-users")
	public ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
	}
	
	//@PreAuthorize(value = "hasRole('ROLE_ADMIN')")
	@PutMapping("/admin/update-role")
	public ResponseEntity<String> updateUserRole(@RequestParam Long userId, @RequestParam String roleName) {
		service.updateUserRole(userId, roleName);
		return new ResponseEntity<>("User updated.", HttpStatus.OK);
	}
	
	public UserDTO getUserById(Long id) {
		return service.getUserById(id);
	}
	
	public void deleteUser(Long id) {
		service.deleteUser(id);
	}

}
