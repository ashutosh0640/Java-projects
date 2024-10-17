package com.spring_security.jdbc.service;

import java.util.List;

import com.spring_security.jdbc.DTO.UserDTO;
import com.spring_security.jdbc.entity.User;

public interface UserService {
	
	public String addUser(User user);
	
	public void updateUserRole(Long userId, String roleName);
	
	public List<User> getAllUsers();
	
	public UserDTO getUserById(Long id);
	
	public void deleteUser(Long id);
	
}
