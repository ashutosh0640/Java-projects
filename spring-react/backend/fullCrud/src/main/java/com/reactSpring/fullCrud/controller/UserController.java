package com.reactSpring.fullCrud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reactSpring.fullCrud.deo.UserDao;
import com.reactSpring.fullCrud.entity.User;
import com.reactSpring.fullCrud.exception.IncorrectPasswordException;
import com.reactSpring.fullCrud.exception.UserNotFoundException;
import com.reactSpring.fullCrud.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping("")
	public UserDao saveUser(@RequestBody User user) {
		UserDao savedUser = service.saveUser(user);
		return savedUser;
	}

	@GetMapping("/all")
	public List<User> findAllUser() {
		List<User> users = service.findAllUser();
		return users;
	}

	@GetMapping("/{id}")
	public Optional<User> findUserById(@PathVariable Long id) {
		Optional<User> user = service.findUserById(id);
		return user;
	}

	@DeleteMapping("/delete/{id}")
	public void deleteUserById(@PathVariable Long id) {
		service.deleteUserById(id);
	}

	@PostMapping("/login")
	public ResponseEntity<?> matchUserPassword(@RequestBody User user) {
		String userId = user.getUserId();
		String password = user.getPassword();

		try {
			UserDao foundUser = service.matchUserPassword(userId, password);
			return ResponseEntity.ok(foundUser);
		} catch (UserNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (IncorrectPasswordException e) {

			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
	}

}
