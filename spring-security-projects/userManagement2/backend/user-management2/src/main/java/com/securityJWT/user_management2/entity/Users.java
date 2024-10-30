package com.securityJWT.user_management2.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Users implements UserDetails{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long user_id;
	
	@Column(unique=true, nullable=true)
	private String username;
	
	@Column(nullable=true)
	private String password;
	
	@ManyToMany
	@JoinTable(
			name="users_roles",
			joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
			)
	private Set<Role> role = new HashSet();
	
	public Users() {
		super();
	}
	
	
	public Users(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Users(String username, String password, Set<Role> role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authoritiesList = role.stream().map(r->new SimpleGrantedAuthority(r.getRole())).collect(Collectors.toList());
		return authoritiesList;
	}
	@Override
	public String getPassword() {
		return this.password;
	}
	@Override
	public String getUsername() {
		return this.username;
	}
	/**
	 * Indicates whether the user's account has expired. An expired account cannot be
	 * authenticated.
	 * @return <code>true</code> if the user's account is valid (ie non-expired),
	 * <code>false</code> if no longer valid (ie expired)
	 */
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * Indicates whether the user is locked or unlocked. A locked user cannot be
	 * authenticated.
	 * @return <code>true</code> if the user is not locked, <code>false</code> otherwise
	 */
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * Indicates whether the user's credentials (password) has expired. Expired
	 * credentials prevent authentication.
	 * @return <code>true</code> if the user's credentials are valid (ie non-expired),
	 * <code>false</code> if no longer valid (ie expired)
	 */
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * Indicates whether the user is enabled or disabled. A disabled user cannot be
	 * authenticated.
	 * @return <code>true</code> if the user is enabled, <code>false</code> otherwise
	 */
	public boolean isEnabled() {
		return true;
	}

}
