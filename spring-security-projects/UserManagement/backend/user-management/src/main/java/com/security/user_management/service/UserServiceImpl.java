package com.security.user_management.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.user_management.entity.Users;
import com.security.user_management.repository.UsersRepository;

import jakarta.transaction.Transactional;


@Service
public class UserServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsersRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users users = userRepo.findByUsername(username)
				.orElseThrow(()->new UsernameNotFoundException("User: "+username+" not found."));
		
		String newUsername = users.getUsername();
		String password = users.getPassword();
		Collection<? extends GrantedAuthority> authorities = users.getAuthorities();
		
		User user = new User(newUsername, password, authorities);
		return user;
	}

	
	public Users saveUsers(Users user) {
	    if (user == null || user.getUsername() == null || user.getPassword() == null) {
	        throw new IllegalArgumentException("Username and password cannot be null");
	    }

	    user.setPassword(passwordEncoder.encode(user.getPassword()));

	    if (user.getRole() == null || user.getRole().isEmpty()) {
	        throw new IllegalArgumentException("User roles cannot be null or empty");
	    }

	    try {
	        Users newUser = userRepo.save(user);
	        return newUser;
	    } catch (DataIntegrityViolationException e) {
	        throw new RuntimeException("User with this username already exists", e);
	    } catch (Exception e) {
	        throw new RuntimeException("Error saving user", e);
	    }
	}
	
	public List<Users> findAllUsers() {
		System.out.println("find all users in service.");
		return userRepo.findAll();
	}
	
	@Transactional
	public Users updateUsersById(long id, Users user) {
		
		Users newUser = userRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found."));
		newUser.setUsername(user.getUsername());
		
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setEmail(user.getEmail());
		newUser.setRole(user.getRole());
		newUser.setCity(user.getCity());
		newUser.setAccountNonExpired(user.isAccountNonExpired());
		newUser.setAccountNonLocked(user.isAccountNonLocked());
		newUser.setCredentialsNonExpired(user.isCredentialsNonExpired());
		newUser.setEnabled(user.isEnabled());
		Users updatedUsers = userRepo.save(newUser);
		return updatedUsers;
	}
	
	public String resetPasswordByUser(String oldPass, String newPass) {
		UserDetails details = getCurrentUserDetails();
		String username = details.getUsername();
		Users user = userRepo.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("User not found."));
		String oldPasswordEncode = passwordEncoder.encode(oldPass);
		if(oldPasswordEncode.equals(user.getPassword())) {
			user.setPassword(passwordEncoder.encode(newPass));
			Users savedUser = userRepo.save(user);
			if(savedUser != null) {
				return "Password save sucessfully.";
			}
		}else {
			return "Incorrect old password.";
		}
		return "Some issue happend. Please try again.";
	}
	
	public String resetPasswordByAdmin(long id, String oldPass, String newPass) {
		UserDetails details = getCurrentUserDetails();
		Collection<? extends GrantedAuthority> authorities = details.getAuthorities();
		boolean flag = authorities.stream().anyMatch(authority -> authorities.equals("ROLE_ADMIN"));
		if(flag) {
			Users user = userRepo.findById(id)
					.orElseThrow(() -> new RuntimeException("User not found."));
			String oldPasswordEncode = passwordEncoder.encode(oldPass);
			if(oldPasswordEncode.equals(user.getPassword())) {
				user.setPassword(passwordEncoder.encode(newPass));
				Users savedUser = userRepo.save(user);
				if(savedUser != null) {
					return "Password save sucessfully.";
				}
			}else {
				return "Incorrect old password.";
			}
			
			
		} else {
			return "User with admin role can change the password.";
		}
		return "Password save sucessfully.";
	}
	
	public void deleteUserById(long id) {
		userRepo.deleteById(id);
	}
	
	
	public UserDetails getCurrentUserDetails() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    System.out.println("authentication: "+authentication.toString());
	    
	    if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
	        return (UserDetails) authentication.getPrincipal();
	    }
	    
	    return null;
	}


}
