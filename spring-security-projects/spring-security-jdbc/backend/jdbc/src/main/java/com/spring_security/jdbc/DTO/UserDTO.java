package com.spring_security.jdbc.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.spring_security.jdbc.entity.Role;

public class UserDTO {
	
	private Long userId;
	private String username;
	private String email;
	private boolean accountNonLocked;
	private boolean accountNonExpired;
	private boolean credentialsNonExpired;
	private boolean enabled;
	private LocalDate credentialsExpiryDate;
	private LocalDate accountExpiryDate;
	private String twoFactorSecret;
	private boolean isTwoRactorEnabled;
	private String signUpMethod;
	private Role role;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	
	public UserDTO() {}

	public UserDTO(Long userId, String username, String email, boolean accountNonLocked, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean enabled, LocalDate credentialsExpiryDate,
			LocalDate accountExpiryDate, String twoFactorSecret, boolean isTwoRactorEnabled, String signUpMethod,
			Role role, LocalDateTime createdDate, LocalDateTime updatedDate) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.accountNonLocked = accountNonLocked;
		this.accountNonExpired = accountNonExpired;
		this.credentialsNonExpired = credentialsNonExpired;
		this.enabled = enabled;
		this.credentialsExpiryDate = credentialsExpiryDate;
		this.accountExpiryDate = accountExpiryDate;
		this.twoFactorSecret = twoFactorSecret;
		this.isTwoRactorEnabled = isTwoRactorEnabled;
		this.signUpMethod = signUpMethod;
		this.role = role;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}
	
	
	
	
	

}
