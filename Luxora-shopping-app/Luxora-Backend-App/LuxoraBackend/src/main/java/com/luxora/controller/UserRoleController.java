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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luxora.entity.UserRole;
import com.luxora.service.UserRoleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/role")
public class UserRoleController {

	@Autowired
	private UserRoleService service;

	private static final Logger logger = LoggerFactory.getLogger(UserRoleController.class);

	// Find user by ID
	@GetMapping("/findById")
	public ResponseEntity<Optional<UserRole>> findById(@RequestParam Integer id) {
		try {
			logger.info("Finding role by ID: {}", id);
			Optional<UserRole> role = service.findById(id);

			// Return 404 if role is not found
			if (role.isEmpty()) {
				logger.warn("Role not found with ID: {}", id);
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>(role, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("Error occurred while finding role by ID: {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Find all roles
	@GetMapping("/findAll")
	public ResponseEntity<List<UserRole>> findAll() {
		try {
			logger.info("Finding all roles");
			List<UserRole> roleList = service.findAll();

			if (roleList.isEmpty()) {
				logger.warn("No roles found");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(roleList, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("Error occurred while finding all roles: {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Find all roles by IDs
	@GetMapping("/findAllById")
	public ResponseEntity<List<UserRole>> findAllById(@RequestParam List<Integer> ids) {
		try {
			logger.info("Finding roles by IDs: {}", ids);
			List<UserRole> roleList = service.findAllById(ids);

			if (roleList.isEmpty()) {
				logger.warn("No roles found for the given IDs: {}", ids);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(roleList, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("Error occurred while finding roles by IDs: {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Find role with pagination
	@GetMapping("/findAllByPage")
	public ResponseEntity<Page<UserRole>> findAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		try {
			logger.info("Fetching roles with pagination - Page: {}, Size: {}", page, size);

			Page<UserRole> rolePage = service.findAll(page, size);

			logger.info("Successfully fetched {} roles", rolePage.getTotalElements());
			return new ResponseEntity<>(rolePage, HttpStatus.OK);

		} catch (Exception ex) {
			logger.error("An error occurred while fetching users: {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Save role
	@PostMapping("/save")
	public ResponseEntity<?> save(@Valid @RequestBody UserRole role) {
		try {
			logger.info("Attempting to save role: {}", role);

			UserRole savedRole = service.save(role);

			logger.info("Role successfully saved with ID: {}", savedRole.getId());
			return new ResponseEntity<>(savedRole, HttpStatus.CREATED);

		} catch (IllegalArgumentException ex) {
			logger.error("Invalid input data: {}", ex.getMessage());
			return new ResponseEntity<>("Invalid input data", HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			logger.error("An error occurred while saving user: {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Save multiple Roles
	@PostMapping("/saveAll")
	public ResponseEntity<List<UserRole>> saveAll(@RequestBody List<UserRole> usersRole) {
		try {
			logger.info("Attempting to save list of Roles");

			List<UserRole> savedRoles = service.saveAll(usersRole);

			return new ResponseEntity<>(savedRoles, HttpStatus.CREATED);

		} catch (Exception ex) {
			logger.error("An error occurred while saving Roles: {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Delete role by ID
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

	// Delete a role
	@DeleteMapping("/delete")
	public ResponseEntity<Void> delete(@RequestBody UserRole role) {
		try {
			service.delete(role);
			logger.info("Deleted role: {}", role);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception ex) {
			logger.error("An error occurred while deleting role: {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Delete all roles by IDs
	@DeleteMapping("/deleteAllById")
	public ResponseEntity<Void> deleteAllById(@RequestParam List<Integer> ids) {
		try {
			service.deleteAllById(ids);
			logger.info("Deleted roles with IDs: {}", ids);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception ex) {
			logger.error("An error occurred while deleting roles by IDs: {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Delete multiple role
	@DeleteMapping("/deleteAll")
	public ResponseEntity<Void> deleteAll(@RequestBody List<UserRole> roles) {
		try {
			service.deleteAll(roles);
			logger.info("Deleted users: {}", roles);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception ex) {
			logger.error("An error occurred while deleting roles: {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Delete all Role
	@DeleteMapping("/deleteAllURole")
	public ResponseEntity<Void> deleteAllURole() {
		try {
			service.deleteAll();
			logger.info("Deleted all role");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception ex) {
			logger.error("An error occurred while deleting all uRole: {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Find role by name
	@GetMapping("/getRoleByName")
	public ResponseEntity<UserRole> findUserRoleByName(@RequestParam String name) {
		try {
			UserRole role = service.findRoleByName(name);
			if (role != null) {
				logger.info("Found role by name: {}", name);
				return new ResponseEntity<>(role, HttpStatus.OK);
			} else {
				logger.info("No role found with name: {}", name);
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception ex) {
			logger.error("An error occurred while finding Role by name: {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/updateRole")
	public ResponseEntity<UserRole> updateUserRoleById(@RequestParam Integer id, @RequestBody UserRole role) {
		logger.info("Updating role details: {}");
		try {
			UserRole u = service.updateUserRoleById(id, role);
			logger.info("Role is updated successfully. {}", u);
			return new ResponseEntity<>(u, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("An error occurred while updating the role: {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
