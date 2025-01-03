package com.shop.luxora.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.luxora.entity.Role;
import com.shop.luxora.repository.RoleRepository;
import com.shop.luxora.service.RoleService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RoleServiceImp implements RoleService {

	private RoleRepository roleRepo;

	public RoleServiceImp(RoleRepository roleRepo) {
		this.roleRepo = roleRepo;
	}

	@Override
	@Transactional
	public List<Role> saveAll(List<Role> roles) {
		if (roles == null || roles.isEmpty()) {
			throw new IllegalArgumentException("Role list cannot be null or empty");
		}

		try {
			return roleRepo.saveAll(roles);
		} catch (Exception e) {
			throw new RuntimeException("Failed to save roles: " + e.getMessage(), e);
		}
	}

	@Override
	public List<Role> findAll() {
		try {
			return roleRepo.findAll();
		} catch (Exception e) {
			throw new RuntimeException("Failed to find roles: " + e.getMessage(), e);
		}
	}

	@Override
	public List<Role> findAllById(Iterable<Integer> ids) {
		List<Role> roles = roleRepo.findAllById(ids);
		if (roles.isEmpty()) {
			throw new EntityNotFoundException("No roles found for the given IDs: " + ids);
		}
		return roles;
	}

	@Override
	public Role save(Role role) {
		try {
			return roleRepo.save(role);
		} catch (Exception e) {
			throw new RuntimeException("Fail to save role.");
		}
	}

	/**
	 * Find a Role by its ID.
	 * 
	 * @param id the ID of the Role.
	 * @return Optional containing the Role if found, or empty Optional otherwise.
	 */
	@Override
    public Role findById(Integer id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive integer.");
        }

        return roleRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found for ID: " + id));
    }
	
	@Override
	public Role findByName(String name) {
		return roleRepo.findByName(name);
	}


    /**
     * Checks if a Role exists by its ID.
     * @param id The ID of the Role to check.
     * @return true if the Role exists, false otherwise.
     */
    public boolean existsById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive integer.");
        }
        return roleRepo.existsById(id);
    }
    
    /**
     * Get the count of all roles.
     * @return count of roles as a long value.
     */
    @Override
    public long getRolesCount() {
        try {
            return roleRepo.count();
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while counting roles.", e);
        }
    }
    
    /**
     * Delete a role by its ID.
     * 
     * @param roleId the ID of the role to delete
     * @throws EntityNotFoundException if the role with the given ID is not found
     */
    @Transactional
    @Override
    public void deleteById(Integer roleId) {
//        Optional<Role> role = roleRepo.findById(roleId);
//        
//        if (!role.isPresent()) {
//            throw new EntityNotFoundException("Role not found with id: " + roleId);
//        }

        roleRepo.deleteById(roleId);
    }
    
    
    /**
     * Delete a role by its Role object.
     * 
     * @param role the object of the role to delete
     * @throws EntityNotFoundException if the role with the given object is not found
     */
    @Transactional
    @Override
    public void deleteRole(Role role) {
        Optional<Role> roleOptional = roleRepo.findById(role.getId());
        
        if (!roleOptional.isPresent()) {
            throw new EntityNotFoundException("Role with ID " + role.getId() + " not found.");
        }

        roleRepo.delete(role);
    }
    

    /**
     * Delete all roles by their IDs.
     * 
     * @param ids List of role IDs to delete
     */
    @Transactional
	@Override
	public void deleteAllByIds(List<Integer> ids) {
		if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("The list of IDs cannot be null or empty.");
        }
		roleRepo.deleteAllById(ids);
		
	}

	@Override
	public void deleteAll(List<Role> roles) {
		if (roles == null || roles.isEmpty()) {
            throw new IllegalArgumentException("Role list cannot be null or empty.");
        }
		roleRepo.deleteAll(roles);
		
	}

	@Override
	public void deleteAll() {
		roleRepo.deleteAll();
	}

	@Override
	public List<Role> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Role> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Role> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}



}
