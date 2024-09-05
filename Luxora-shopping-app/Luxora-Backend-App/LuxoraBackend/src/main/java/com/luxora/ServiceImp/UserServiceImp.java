package com.luxora.ServiceImp;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.luxora.entity.User;
import com.luxora.repository.UserRepository;
import com.luxora.security.EncodePassword;
import com.luxora.service.UserService;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private EncodePassword encodePassword;

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

	// Find user
	@Override
	public Optional<User> findById(Integer id) {
		logger.debug("Finding user by ID: {}", id);
		try {
			Optional<User> user = repo.findById(id);
			if (user.isPresent()) {
				logger.debug("User found: {}", user.get());
			} else {
				logger.debug("User with ID {} not found", id);
			}
			return user;
		} catch (Exception ex) {
			logger.error("Error finding user by ID: {}", id, ex);
			throw new RuntimeException("Error finding user by ID", ex);
		}
	}

	@Override
	public List<User> findAll() {
		logger.info("Starting to find all users.");
		try {
			List<User> users = repo.findAll();
			if (users.isEmpty()) {
				logger.warn("No users found.");
			} else {
				logger.debug("Found {} users.", users.size());
			}
			return users;
		} catch (Exception ex) {
			logger.error("Error occurred while finding all users: {}", ex.getMessage(), ex);
			throw new RuntimeException("Failed to retrieve users from the database", ex);
		}
	}

	@Override
	public List<User> findAllById(List<Integer> ids) {
		logger.info("Finding users by IDs: {}", ids);
		try {
			if (ids == null || ids.isEmpty()) {
				logger.warn("Empty or null ID list provided.");
				return Collections.emptyList();
			}
			List<User> users = repo.findAllById(ids);
			logger.debug("Found {} users for provided IDs.", users.size());
			return users;
		} catch (Exception ex) {
			logger.error("Error occurred while finding users by IDs: {}", ex.getMessage(), ex);
			throw new RuntimeException("Failed to retrieve users by IDs", ex);
		}
	}

	@Override
	public Page<User> findAll(int page, int size) {
		logger.info("Finding users with pagination. Page: {}, Size: {}", page, size);
		try {
			page = Math.max(page, 0);
			size = Math.max(size, 10);
			PageRequest pageable = PageRequest.of(page, size);
			Page<User> userPage = repo.findAll(pageable);
			logger.debug("Found {} users on page {}.", userPage.getNumberOfElements(), page);
			return userPage;
		} catch (Exception ex) {
			logger.error("Error occurred while finding users with pagination: {}", ex.getMessage(), ex);
			throw new RuntimeException("Failed to retrieve users with pagination", ex);
		}
	}

	// Save user
	@Override
	public User save(User user) {
		logger.info("Saving user: {}", user);
		try {
			
			String rawPass = user.getPassword();
			
			String encodedPass = encodePassword.passwordEncoder().encode(rawPass);
			
			user.setPassword(encodedPass);
			
			User savedUser = repo.save(user);
			logger.debug("User saved successfully: {}", savedUser);
			return savedUser;
		} catch (Exception ex) {
			logger.error("Error occurred while saving user: {}", ex.getMessage(), ex);
			throw new RuntimeException("Failed to save user", ex);
		}
	}

	@Override
	public List<User> saveAll(List<User> users) {
		logger.info("Saving multiple users. Count: {}", users.size());
		try {
			if (users == null || users.isEmpty()) {
				logger.warn("Empty or null user list provided.");
				return Collections.emptyList();
			}
			List<User> savedUsers = repo.saveAll(users);
			logger.debug("Users saved successfully. Count: {}", savedUsers.size());
			return savedUsers;
		} catch (Exception ex) {
			logger.error("Error occurred while saving users: {}", ex.getMessage(), ex);
			throw new RuntimeException("Failed to save users", ex);
		}
	}

	// Delete user
	@Override
	public void deleteById(Integer id) {
		logger.info("Deleting user by ID: {}", id);
		try {
			repo.deleteById(id);
			logger.debug("User deleted successfully. ID: {}", id);
		} catch (Exception ex) {
			logger.error("Error occurred while deleting user by ID: {}", ex.getMessage(), ex);
			throw new RuntimeException("Failed to delete user by ID", ex);
		}
	}

	@Override
	public void delete(User user) {
		logger.info("Deleting user: {}", user);
		try {
			repo.delete(user);
			logger.debug("User deleted successfully: {}", user);
		} catch (Exception ex) {
			logger.error("Error occurred while deleting user: {}", ex.getMessage(), ex);
			throw new RuntimeException("Failed to delete user", ex);
		}
	}

	@Override
	public void deleteAllById(List<Integer> ids) {
		logger.info("Deleting users by IDs: {}", ids);
		try {
			if (ids == null || ids.isEmpty()) {
				logger.warn("Empty or null ID list provided.");
				return;
			}
			repo.deleteAllById(ids);
			logger.debug("Users deleted successfully. IDs: {}", ids);
		} catch (Exception ex) {
			logger.error("Error occurred while deleting users by IDs: {}", ex.getMessage(), ex);
			throw new RuntimeException("Failed to delete users by IDs", ex);
		}
	}

	@Override
	public void deleteAll(List<User> users) {
		logger.info("Deleting multiple users. Count: {}", users.size());
		try {
			if (users == null || users.isEmpty()) {
				logger.warn("Empty or null user list provided.");
				return;
			}
			repo.deleteAll(users);
			logger.debug("Users deleted successfully. Count: {}", users.size());
		} catch (Exception ex) {
			logger.error("Error occurred while deleting users: {}", ex.getMessage(), ex);
			throw new RuntimeException("Failed to delete users", ex);
		}
	}

	@Override
	public void deleteAll() {
		logger.info("Deleting all users.");
		try {
			repo.deleteAll();
			logger.debug("All users deleted successfully.");
		} catch (Exception ex) {
			logger.error("Error occurred while deleting all users: {}", ex.getMessage(), ex);
			throw new RuntimeException("Failed to delete all users", ex);
		}
	}

	// Find by email and number
	@Override
	public User getUserByEmail(String email) {
		logger.info("Finding user by email: {}", email);
		try {
			User user = repo.getUserByEmail(email);
			if (user == null) {
				logger.warn("No user found with email: {}", email);
			} else {
				logger.debug("User found with email: {}", email);
			}
			return user;
		} catch (Exception ex) {
			logger.error("Error occurred while finding user by email: {}", ex.getMessage(), ex);
			throw new RuntimeException("Failed to retrieve user by email", ex);
		}
	}

	@Override
	public User getUserByMobileNumber(String mobile) {
		logger.info("Finding user by mobile number: {}", mobile);
		try {
			User user = repo.getUserByMobileNumber(mobile);
			if (user == null) {
				logger.warn("No user found with mobile number: {}", mobile);
			} else {
				logger.debug("User found with mobile number: {}", mobile);
			}
			return user;
		} catch (Exception ex) {
			logger.error("Error occurred while finding user by mobile number: {}", ex.getMessage(), ex);
			throw new RuntimeException("Failed to retrieve user by mobile number", ex);
		}
	}
	

	/**
     * Updating user's first name, last name, email, mobile, username etc.
     * 
     * @param user details.
     * @return updated user.
     */
	@Override
	public User updateUserById(Integer id, User user) {
		logger.info("Editing user by ID: {}", id);
		try {
			Optional<User> existingUser = repo.findById(id);
			if (existingUser.isPresent()) {
				User updatedUser = existingUser.get();
				updatedUser.setFirstName(user.getFirstName());
				updatedUser.setLastName(user.getLastName());
				updatedUser.setEmail(user.getEmail());
				updatedUser.setMobileNumber(user.getMobileNumber());
				updatedUser.setUsername(user.getUsername());
				User savedUser = repo.save(updatedUser);
				logger.debug("User updated successfully. ID: {}", id);
				return savedUser;
			} else {
				logger.warn("No user found with ID: {}", id);
				throw new RuntimeException("User not found with ID: " + id);
			}
		} catch (Exception ex) {
			logger.error("Error occurred while editing user by ID: {}", ex.getMessage(), ex);
			throw new RuntimeException("Failed to edit user", ex);
		}
	}
	
	 /**
     * Searches for users based on first name, last name, mobile, or email.
     * 
     * @param searchTerm The term to search for in any of the user fields.
     * @return A list of users matching the search criteria.
     */
	@Override
    public List<User> searchUsers(String searchTerm) {
    	logger.info("Searching User using term: {}", searchTerm);
    	try {
    		return repo.searchByAnyField(searchTerm);
    	} catch (Exception ex) {
    		logger.error("Error occurred while searching user by this term: {}", ex.getMessage(), ex);
			throw new RuntimeException("Failed to search user", ex);
    		
    	}
	}
	
	
	@Override
	public Page<User> findAllByPage(int pageNum, int pageSize, String[] properties, String direction) {
	    logger.info("Finding users from page {} with size {}.", pageNum, pageSize);
	    try {
	        
	        Sort sort = Sort.by(properties);
	        
	        sort = properties != null && properties.length > 0 && "asc".equalsIgnoreCase(direction) ? sort.ascending() : sort.descending();

	        
	        PageRequest pageable = PageRequest.of(pageNum, pageSize, sort);
	        
	        Page<User> userPage = repo.findAll(pageable);
	        return userPage;
	    } catch (IllegalArgumentException ex) {
	        logger.error("Invalid argument: {}", ex.getMessage());
	        throw ex;
	    } catch (Exception ex) {
	        logger.error("Error occurred while finding users: {}", ex.getMessage());
	        throw new RuntimeException("Failed to find users", ex);
	    }
	}
	
}
