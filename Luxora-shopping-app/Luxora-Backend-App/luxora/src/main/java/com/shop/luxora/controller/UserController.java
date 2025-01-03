package com.shop.luxora.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.luxora.entity.User;
import com.shop.luxora.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
	
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	

	
	
	@PostMapping("/saveAll")
	public ResponseEntity<?> saveAll(@RequestBody List<User> userlist) {
		try {
			List<User> saveList =  userService.saveAll(userlist);
			return ResponseEntity.ok(saveList);
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error happend.\n"+ ex.getMessage());
		}
		
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> findAll() {
		try {
			List<User> userList = userService.findAll();
			return ResponseEntity.ok(userList);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occured.\n"+ex.getMessage());
		}
	}
	
	@PostMapping("/findAllById")
	public ResponseEntity<?> findAllById(@RequestBody List<Long> ids) {
		try {
			List<User> userList = userService.findAllById(ids);
			return ResponseEntity.ok(userList);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occured.\n"+ex.getMessage());
		}
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody User user) {
		try {
			User saveUser = userService.save(user);
			return ResponseEntity.ok(saveUser);
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(user);
    }
	

}
