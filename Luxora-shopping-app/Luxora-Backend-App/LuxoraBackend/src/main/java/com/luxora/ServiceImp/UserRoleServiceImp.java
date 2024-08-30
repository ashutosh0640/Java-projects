package com.luxora.ServiceImp;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.luxora.entity.UserRole;
import com.luxora.repository.UserRoleRepository;
import com.luxora.service.UserRoleService;

@Service
public class UserRoleServiceImp implements UserRoleService{
	
	@Autowired
	private UserRoleRepository repo;
	
	private static final Logger logger = LoggerFactory.getLogger(UserRoleServiceImp.class);

	@Override
	public Optional<UserRole> findById(Integer id) {
	    logger.info("Finding UserRole by ID: {}", id);
	    try {
	        if (id == null) {
	            logger.warn("Null ID provided.");
	            return Optional.empty();
	        }
	        Optional<UserRole> userRole = repo.findById(id);
	        if (userRole.isPresent()) {
	            logger.debug("UserRole found with ID: {}", id);
	        } else {
	            logger.warn("No UserRole found with ID: {}", id);
	        }
	        return userRole;
	    } catch (Exception ex) {
	        logger.error("Error occurred while finding UserRole by ID: {}", ex.getMessage(), ex);
	        throw new RuntimeException("Failed to find UserRole by ID", ex);
	    }
	}


	@Override
	public List<UserRole> findAll() {
	    logger.info("Finding all UserRoles.");
	    try {
	        List<UserRole> userRoles = repo.findAll();
	        logger.debug("Found {} UserRoles.", userRoles.size());
	        return userRoles;
	    } catch (Exception ex) {
	        logger.error("Error occurred while finding all UserRoles: {}", ex.getMessage(), ex);
	        throw new RuntimeException("Failed to find all UserRoles", ex);
	    }
	}


	@Override
	public List<UserRole> findAllById(List<Integer> ids) {
	    logger.info("Finding UserRoles by IDs: {}", ids);
	    try {
	        if (ids == null || ids.isEmpty()) {
	            logger.warn("Empty or null ID list provided.");
	            return Collections.emptyList();
	        }
	        List<UserRole> userRoles = repo.findAllById(ids);
	        logger.debug("Found {} UserRoles for provided IDs.", userRoles.size());
	        return userRoles;
	    } catch (Exception ex) {
	        logger.error("Error occurred while finding UserRoles by IDs: {}", ex.getMessage(), ex);
	        throw new RuntimeException("Failed to find UserRoles by IDs", ex);
	    }
	}


	@Override
	public Page<UserRole> findAll(int page, int size) {
	    logger.info("Finding UserRoles with pagination. Page: {}, Size: {}", page, size);
	    try {
	        page = Math.max(page, 0);
	        size = Math.max(size, 10);
	        PageRequest pageable = PageRequest.of(page, size);
	        Page<UserRole> userRolePage = repo.findAll(pageable);
	        logger.debug("Found {} UserRoles on page {}.", userRolePage.getNumberOfElements(), page);
	        return userRolePage;
	    } catch (Exception ex) {
	        logger.error("Error occurred while finding UserRoles with pagination: {}", ex.getMessage(), ex);
	        throw new RuntimeException("Failed to find UserRoles with pagination", ex);
	    }
	}


	@Override
	public UserRole save(UserRole userRole) {
	    logger.info("Saving UserRole: {}", userRole);
	    try {
	        //validateUserRole(userRole); // Optional validation method
	        UserRole savedUserRole = repo.save(userRole);
	        logger.debug("UserRole saved successfully: {}", savedUserRole);
	        return savedUserRole;
	    } catch (Exception ex) {
	        logger.error("Error occurred while saving UserRole: {}", ex.getMessage(), ex);
	        throw new RuntimeException("Failed to save UserRole", ex);
	    }
	}


	@Override
	public List<UserRole> saveAll(List<UserRole> roles) {
	    logger.info("Saving multiple UserRoles. Count: {}", roles.size());
	    try {
	        if (roles == null || roles.isEmpty()) {
	            logger.warn("Empty or null UserRole list provided.");
	            return Collections.emptyList();
	        }
	        List<UserRole> savedRoles = repo.saveAll(roles);
	        logger.debug("UserRoles saved successfully. Count: {}", savedRoles.size());
	        return savedRoles;
	    } catch (Exception ex) {
	        logger.error("Error occurred while saving UserRoles: {}", ex.getMessage(), ex);
	        throw new RuntimeException("Failed to save UserRoles", ex);
	    }
	}


	@Override
	public void deleteById(Integer id) {
	    logger.info("Deleting UserRole by ID: {}", id);
	    try {
	        if (id == null) {
	            logger.warn("Null ID provided.");
	            return;
	        }
	        repo.deleteById(id);
	        logger.debug("UserRole deleted successfully. ID: {}", id);
	    } catch (Exception ex) {
	        logger.error("Error occurred while deleting UserRole by ID: {}", ex.getMessage(), ex);
	        throw new RuntimeException("Failed to delete UserRole by ID", ex);
	    }
	}


	@Override
	public void delete(UserRole userRole) {
	    logger.info("Deleting UserRole: {}", userRole);
	    try {
	        if (userRole == null) {
	            logger.warn("Null UserRole provided.");
	            return;
	        }
	        repo.delete(userRole);
	        logger.debug("UserRole deleted successfully: {}", userRole);
	    } catch (Exception ex) {
	        logger.error("Error occurred while deleting UserRole: {}", ex.getMessage(), ex);
	        throw new RuntimeException("Failed to delete UserRole", ex);
	    }
	}


	@Override
	public void deleteAllById(List<Integer> ids) {
	    logger.info("Deleting UserRoles by IDs: {}", ids);
	    try {
	        if (ids == null || ids.isEmpty()) {
	            logger.warn("Empty or null ID list provided.");
	            return;
	        }
	        repo.deleteAllById(ids);
	        logger.debug("UserRoles deleted successfully. IDs: {}", ids);
	    } catch (Exception ex) {
	        logger.error("Error occurred while deleting UserRoles by IDs: {}", ex.getMessage(), ex);
	        throw new RuntimeException("Failed to delete UserRoles by IDs", ex);
	    }
	}


	@Override
	public void deleteAll(List<UserRole> roles) {
	    logger.info("Deleting multiple UserRoles. Count: {}", roles.size());
	    try {
	        if (roles == null || roles.isEmpty()) {
	            logger.warn("Empty or null UserRole list provided.");
	            return;
	        }
	        repo.deleteAll(roles);
	        logger.debug("UserRoles deleted successfully. Count: {}", roles.size());
	    } catch (Exception ex) {
	        logger.error("Error occurred while deleting UserRoles: {}", ex.getMessage(), ex);
	        throw new RuntimeException("Failed to delete UserRoles", ex);
	    }
	}


	@Override
	public void deleteAll() {
	    logger.info("Deleting all UserRoles.");
	    try {
	        repo.deleteAll();
	        logger.debug("All UserRoles deleted successfully.");
	    } catch (Exception ex) {
	        logger.error("Error occurred while deleting all UserRoles: {}", ex.getMessage(), ex);
	        throw new RuntimeException("Failed to delete all UserRoles", ex);
	    }
	}


	@Override
	public UserRole findRoleByName(String name) {
	    logger.info("Finding UserRole by name: {}", name);
	    try {
	        if (name == null || name.isEmpty()) {
	            logger.warn("Empty or null name provided.");
	            return null;
	        }
	        UserRole userRole = repo.findRoleByName(name);
	        if (userRole == null) {
	            logger.warn("No UserRole found with name: {}", name);
	        } else {
	            logger.debug("UserRole found with name: {}", name);
	        }
	        return userRole;
	    } catch (Exception ex) {
	        logger.error("Error occurred while finding UserRole by name: {}", ex.getMessage(), ex);
	        throw new RuntimeException("Failed to find UserRole by name", ex);
	    }
	}


	@Override
	public UserRole updateUserRoleById(Integer id, UserRole role) {
	    logger.info("Editing UserRole by ID: {}", id);
	    try {
	        Optional<UserRole> existingRole = repo.findById(id);
	        if (existingRole.isPresent()) {
	            UserRole updatedRole = existingRole.get();
	            // Update fields here (e.g., updatedRole.setRoleName(role.getRoleName());)
	            UserRole savedRole = repo.save(updatedRole);
	            logger.debug("UserRole updated successfully. ID: {}", id);
	            return savedRole;
	        } else {
	            logger.warn("No UserRole found with ID: {}", id);
	            throw new RuntimeException("UserRole not found with ID: " + id);
	        }
	    } catch (Exception ex) {
	        logger.error("Error occurred while editing UserRole by ID: {}", ex.getMessage(), ex);
	        throw new RuntimeException("Failed to edit UserRole", ex);
	    }
	}


}
