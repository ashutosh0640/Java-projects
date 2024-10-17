package com.spring_security.jdbc.security.service;


import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring_security.jdbc.entity.User;

public class UserDetailsImpl implements UserDetails{
	
	private static final long serialVersionUID  = 1L;
	
	private Long id;
	private String username;
	private String email;
	
	@JsonIgnore
	private String password;
	
	private boolean is2faEnabled;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	
	public UserDetailsImpl() {}


	public UserDetailsImpl(Long id, String username, String email, String password, boolean is2faEnabled,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.is2faEnabled = is2faEnabled;
		this.authorities = authorities;
	}
	
	
	public static UserDetailsImpl build(User user) {
	    GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getRole().name());
	    return new UserDetailsImpl(
	        user.getUserId(),
	        user.getUsername(),
	        user.getEmail(),
	        user.getPassword(),
	        user.isTwoFactorEnabled(),
	        List.of(authority)
	    );
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}


	@Override
	public String getPassword() {
		return password;
	}


	@Override
	public String getUsername() {
		return username;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	
	@Override
	public boolean isEnabled() {
		return true;
	}
	

	public Long getId() {
		return id;
	}


	public String getEmail() {
		return email;
	}


	public boolean isIs2faEnabled() {
		return is2faEnabled;
	}


	@Override
	public int hashCode() {
		return Objects.hash(authorities, email, id, is2faEnabled, password, username);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDetailsImpl other = (UserDetailsImpl) obj;
		return Objects.equals(authorities, other.authorities) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && is2faEnabled == other.is2faEnabled
				&& Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}
	
}
