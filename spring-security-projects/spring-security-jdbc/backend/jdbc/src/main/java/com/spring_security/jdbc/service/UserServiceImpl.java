package com.spring_security.jdbc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring_security.jdbc.DTO.UserDTO;
import com.spring_security.jdbc.entity.AppRoles;
import com.spring_security.jdbc.entity.Role;
import com.spring_security.jdbc.entity.User;
import com.spring_security.jdbc.repository.RoleRepository;
import com.spring_security.jdbc.repository.UsersRepository;


@Service
public class UserServiceImpl  implements UserService {
	
	private UsersRepository userRepo;
	private RoleRepository roleRepo;

	@Autowired
	public UserServiceImpl(UsersRepository userRepo, RoleRepository roleRepo) {
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
	}

	@Override
	public void updateUserRole(Long userId, String roleName) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found."));
		AppRoles appRole = AppRoles.valueOf(roleName);
		Role role = roleRepo.findByRole(appRole)
				.orElseThrow(() -> new RuntimeException("Role not found"));
		user.setRole(role);
		userRepo.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}
	
	@Override
	public UserDTO getUserById(Long id) {
		User user = userRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found."));
		UserDTO userDto = convertToDto(user);
		return userDto;
	}
	
	@Override
	public String addUser(User user) {
		userRepo.save(user);
		return "User save sucessfully";
	}

	@Override
	public void deleteUser(Long id) {
		userRepo.deleteById(id);
		
	}
	
	private UserDTO convertToDto(User user) {
		return new UserDTO(
				user.getUserId(),
				user.getUsername(),
				user.getEmail(),
				user.isAccountNonLocked(),
				user.isAccountNonExpired(),
				user.isCredentialNonExpired(),
				user.isEnable(),
				user.getCredentialsExpiryDate(),
				user.getAccountExpiryDate(),
				user.getTwoFactorSecret(),
				user.isTwoFactorEnabled(),
				user.getSignUpMethod(),
				user.getRole(),
				user.getCreateDate(),
				user.getUpdatedate()
				
				);	
	}
	
}
