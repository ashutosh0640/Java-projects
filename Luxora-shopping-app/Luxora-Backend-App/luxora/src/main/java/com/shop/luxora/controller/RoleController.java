package com.shop.luxora.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.shop.luxora.entity.Role;
import com.shop.luxora.service.RoleService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("role")
public class RoleController {
	
	private RoleService roleService;

	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}


	@PostMapping("/saveAll")
    public ResponseEntity<?> saveAllRoles(@RequestBody List<Role> roles) {
        try {
            if (roles == null || roles.isEmpty()) {
                return ResponseEntity.badRequest().body("Role list cannot be null or empty");
            }

            List<Role> savedRoles = roleService.saveAll(roles);
            return ResponseEntity.ok(savedRoles);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("An error occurred while saving roles: " + e.getMessage());
        }
    }
	
	
	@GetMapping("")
	public ResponseEntity<?> findAll() {
		try {
            List<Role> findRoles = roleService.findAll();
            return ResponseEntity.ok(findRoles);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("An error occurred while finding	 roles: " + e.getMessage());
        }
	}
	
	
	@PostMapping("/findAllById")
    public ResponseEntity<?> findAllById(@RequestBody List<Integer> ids) {
        try {
            List<Role> roles = roleService.findAllById(ids);
            return ResponseEntity.ok(roles);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Roles not found for given Ids.\n"+ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.\n"+ex.getMessage());
        }
    }
	
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody Role role) {
		if(role == null || role.getName().isEmpty()) {
			return ResponseEntity.badRequest().body("Role can not be null or empty.");
		}
		
		try {
			Role savedRole = roleService.save(role);
			return ResponseEntity.ok(savedRole);
		} catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while saving role: " + ex.getMessage());
		}
		
	}
	

    /**
     * Endpoint to retrieve a Role by its ID.
     * 
     * @param id the ID of the Role
     * @return ResponseEntity containing the Role if found, or an error message otherwise
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        Role role = roleService.findById(id);
        return ResponseEntity.ok(role);
    }
    
    @GetMapping("/name/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name) {
    	Role role = roleService.findByName(name);
    	return ResponseEntity.ok(role);
    }
    
    
    /**
     * API endpoint to check if a Role exists by its ID.
     * @param id The ID of the Role.
     * @return ResponseEntity with a success message if the Role exists, or an error message if it doesn't.
     */
    @GetMapping("/exists/{id}")
    public ResponseEntity<String> existsById(@PathVariable Integer id) {
        try {
            boolean exists = roleService.existsById(id);
            if (exists) {
                return ResponseEntity.ok("Role with ID " + id + " exists.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body("Role with ID " + id + " does not exist.");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body(e.getMessage());
        }
    }
    
    /**
     * API to get the count of roles.
     * @return ResponseEntity with the count of roles.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getRolesCount() {
        try {
            long count = roleService.getRolesCount();
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
    
    /**
     * Delete a role by its ID.
     * 
     * @param roleId the ID of the role to delete
     * @return a ResponseEntity indicating the result of the operation
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer roleId) {
        try {
            roleService.deleteById(roleId);
            return ResponseEntity.status(HttpStatus.OK).body("Role deleted successfully.");
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
    
    @DeleteMapping("/role")
    public ResponseEntity<String> deleteRole(@RequestBody Role role) {
        try {
            roleService.deleteRole(role);
            return ResponseEntity.ok("Role deleted successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the role.");
        }
    }
    
    
    @DeleteMapping("/ids")
    public ResponseEntity<String> deleteAllByIds(@RequestBody List<Integer> ids) {
    	try {
    		roleService.deleteAllByIds(ids);
    		return ResponseEntity.ok("All ID's role deleted successfully.");
    	} catch (IllegalArgumentException ex) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    	}
    }
    
    @DeleteMapping("/roles")
    public ResponseEntity<String> deleteAll(List<Role> roles) {
    	try {
    		roleService.deleteAll(roles);
    		return ResponseEntity.ok("All roles deleted successfully.");
    	} catch (IllegalArgumentException ex) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    	}
    }
    
    
    
    @DeleteMapping("/delete-all")
    public void deleteAll() {
    	roleService.deleteAll();
    }

}
