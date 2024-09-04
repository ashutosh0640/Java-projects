package com.luxora.controller;

import java.util.Arrays;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luxora.ServiceImp.UserServiceImp;
import com.luxora.entity.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserServiceImp service;

	// Find user by ID
	@GetMapping("/findById")
	public ResponseEntity<Optional<User>> findById(@RequestParam Integer id) {
		try {
			logger.info("Finding user by ID: {}", id);
			Optional<User> user = service.findById(id);

			// Return 404 if user is not found
			if (user.isEmpty()) {
				logger.warn("User not found with ID: {}", id);
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("Error occurred while finding user by ID: {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Find all users
	@GetMapping("/findAll")
	public ResponseEntity<List<User>> findAll() {
		try {
			logger.info("Finding all users");
			List<User> userList = service.findAll();

			if (userList.isEmpty()) {
				logger.warn("No users found");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(userList, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("Error occurred while finding all users: {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Find all users by IDs
	@GetMapping("/findAllById")
	public ResponseEntity<List<User>> findAllById(@RequestParam List<Integer> ids) {
		try {
			logger.info("Finding users by IDs: {}", ids);
			List<User> userList = service.findAllById(ids);

			if (userList.isEmpty()) {
				logger.warn("No users found for the given IDs: {}", ids);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(userList, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("Error occurred while finding users by IDs: {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Find users with pagination
	@GetMapping("/findAllByPage")
	public ResponseEntity<Page<User>> findAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		try {
			logger.info("Fetching users with pagination - Page: {}, Size: {}", page, size);

			Page<User> userPage = service.findAll(page, size);

			logger.info("Successfully fetched {} users", userPage.getTotalElements());
			return new ResponseEntity<>(userPage, HttpStatus.OK);

		} catch (Exception ex) {
			logger.error("An error occurred while fetching users: {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Save user
	@PostMapping("/save")
	public ResponseEntity<?> save(@Valid @RequestBody User user) {
		try {
			logger.info("Attempting to save user: {}", user);

			User savedUser = service.save(user);

			logger.info("User successfully saved with ID: {}", savedUser.getId());
			return new ResponseEntity<>(savedUser, HttpStatus.CREATED);

		} catch (IllegalArgumentException ex) {
			logger.error("Invalid input data: {}", ex.getMessage());
			return new ResponseEntity<>("Invalid input data", HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			logger.error("An error occurred while saving user: {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Save multiple users
	@PostMapping("/saveAll")
	public ResponseEntity<List<User>> saveAll(@RequestBody List<User> users) {
		try {
			logger.info("Attempting to save list of users");

			List<User> savedUsers = service.saveAll(users);

			return new ResponseEntity<>(savedUsers, HttpStatus.CREATED);

		} catch (Exception ex) {
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
	
	
	@PutMapping("/updateUser")
	public ResponseEntity<User> updateUser(@RequestParam Integer id, @RequestBody User user) {
		logger.info("Updating user information: {}");
		try {
			User u = service.updateUserById(id, user);
			logger.info("User is updated successfully. {}", u);
			return new ResponseEntity<>(u, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("An error occurred while updating the user: {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/checkEmail")
	public ResponseEntity<Boolean> checkEmailIsNotDuplicate(@RequestParam String email) {
	    logger.info("Checking for duplicate email: {}", email);
	    try {
	        User user = service.getUserByEmail(email);
	        boolean isDuplicate = user == null;
	        return new ResponseEntity<>(isDuplicate, HttpStatus.OK);
	    } catch (Exception ex) {
	        logger.error("An error occurred while checking for duplicate email: {}", ex.getMessage());
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	
	@GetMapping("/checkMobile")
	public ResponseEntity<Boolean> ckeckPhoneIsNotDuplicate(@RequestParam String mobile) {
		logger.info("Checking for duplicate mobile number: {}", mobile);
		try {
			User user = service.getUserByMobileNumber(mobile);
			boolean isDuplicate = user==null;
			return new ResponseEntity<>(isDuplicate, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("An error occurred while checking for duplicate mobile number: {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
    /**
     * Endpoint to search for users based on any field.
     * 
     * @param searchTerm The term to search for.
     * @return A list of users matching the search criteria.
     */
    @GetMapping("/searchUsers")
    public ResponseEntity<List<User>> searchUsers(@RequestParam String searchTerm) {
        List<User> users = service.searchUsers(searchTerm);
        return ResponseEntity.ok(users);
    }
    
    @GetMapping("findAll/page")
    public ResponseEntity<Page<User>> findAllByPage(
            @RequestParam(defaultValue = "0") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam String[] properties,
            @RequestParam(defaultValue = "asc") String direction) {

        logger.info("Request received to find users: page={}, size={}, properties={}, direction={}", 
                     pageNum, pageSize, Arrays.toString(properties), direction);
        try {
            Page<User> userPage = service.findAllByPage(pageNum, pageSize, properties, direction);
            return new ResponseEntity<>(userPage, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            logger.error("Invalid request parameters: {}", ex.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            logger.error("Error occurred while retrieving users: {}", ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
