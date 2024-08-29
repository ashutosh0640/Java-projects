package com.luxora.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luxora.ServiceImp.UserServiceImp;
import com.luxora.entity.User;

@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserServiceImp service;

	@GetMapping("/findById")
	public ResponseEntity<Optional<User>> findById(@RequestParam Integer id) {
		Optional<User> user = service.findById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<User>> findAll() {
		List<User> userList = service.findAll();
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}

	@GetMapping("/findAllById")
	public ResponseEntity<List<User>> findAllById(@RequestParam List<Integer> ids) {
		List<User> userList = service.findAllById(ids);

		return userList.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(userList, HttpStatus.OK);
	}

	@GetMapping("/findAllByPage")
	public ResponseEntity<Page<User>> findAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {

		Page<User> userPage = service.findAll(page, size);

		return new ResponseEntity<>(userPage, HttpStatus.OK);
	}

	// Save user
	@PostMapping("/save")
	public ResponseEntity<User> save(@RequestBody User user) {
		User saveUser = service.save(user);
		return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
	}

	// Save multiple users
	@PostMapping("/saveAll")
	public ResponseEntity<List<User>> saveAll(@RequestBody List<User> users) {
		try {
			// Logging the incoming request
			logger.info("Attempting to save list of users");

			// Save all users
			List<User> savedUsers = service.saveAll(users);

			// Return the saved list of users with CREATED status
			return new ResponseEntity<>(savedUsers, HttpStatus.CREATED);

		} catch (Exception ex) {
			// Handle exceptions and log the error
			logger.error("An error occurred while saving users: {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Delete user by ID
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
		try {
			service.deleteById(id);
			logger.info("Deleted user with ID: {}", id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception ex) {
			logger.error("An error occurred while deleting user by ID: {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Delete a user
	@DeleteMapping("/delete")
	public ResponseEntity<Void> delete(@RequestBody User user) {
		try {
			service.delete(user);
			logger.info("Deleted user: {}", user);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception ex) {
			logger.error("An error occurred while deleting user: {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Delete all users by IDs
	@DeleteMapping("/deleteAllById")
	public ResponseEntity<Void> deleteAllById(@RequestParam List<Integer> ids) {
		try {
			service.deleteAllById(ids);
			logger.info("Deleted users with IDs: {}", ids);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception ex) {
			logger.error("An error occurred while deleting users by IDs: {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Delete multiple users
	@DeleteMapping("/deleteAll")
	public ResponseEntity<Void> deleteAll(@RequestBody List<User> users) {
		try {
			service.deleteAll(users);
			logger.info("Deleted users: {}", users);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception ex) {
			logger.error("An error occurred while deleting users: {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Delete all users
	@DeleteMapping("/deleteAllUsers")
	public ResponseEntity<Void> deleteAllUsers() {
		try {
			service.deleteAll();
			logger.info("Deleted all users");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception ex) {
			logger.error("An error occurred while deleting all users: {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Find user by email
	@GetMapping("/getUserByEmail")
	public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
		try {
			User user = service.getUserByEmail(email);
			if (user != null) {
				logger.info("Found user by email: {}", email);
				return new ResponseEntity<>(user, HttpStatus.OK);
			} else {
				logger.info("No user found with email: {}", email);
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception ex) {
			logger.error("An error occurred while finding user by email: {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Find user by mobile number
	@GetMapping("/getUserByMobileNumber")
	public ResponseEntity<User> getUserByMobileNumber(@RequestParam String mobile) {
		try {
			User user = service.getUserByMobileNumber(mobile);
			if (user != null) {
				logger.info("Found user by mobile number: {}", mobile);
				return new ResponseEntity<>(user, HttpStatus.OK);
			} else {
				logger.info("No user found with mobile number: {}", mobile);
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception ex) {
			logger.error("An error occurred while finding user by mobile number: {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
