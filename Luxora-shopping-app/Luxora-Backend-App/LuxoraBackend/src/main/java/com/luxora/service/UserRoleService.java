package com.luxora.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.luxora.entity.UserRole;

public interface UserRoleService {

	// Find user
	public Optional<UserRole> findById(Integer id);

	public List<UserRole> findAll();

	public List<UserRole> findAllById(List<Integer> Ids);

	public Page<UserRole> findAll(int page, int size);

	// Save user
	public UserRole save(UserRole user);

	public List<UserRole> saveAll(List<UserRole> roles);

	// Delete user
	public void deleteById(Integer id);

	public void delete(UserRole roles);

	public void deleteAllById(List<Integer> ids);

	public void deleteAll(List<UserRole> roles);

	public void deleteAll();

	// Find by email and number
	public UserRole findRoleByName(String name);
	
	
	//Edit user role
	public UserRole updateUserRoleById(Integer id, UserRole role);


}
