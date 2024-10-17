package com.spring_security.jdbc.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users",
uniqueConstraints = {
		@UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email")
})
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private long userId;
	
	@NotBlank
	@Size(max=20)
	@Column(name = "username", nullable = false, unique = true, length = 50 )
	private String username;
	
	@NotBlank
	@Email
	@Size(max=60)
	@Column(name="email")
	private String email;
	
	@Size(max=120)
	@JsonIgnore
	@Column(name = "password", nullable=false, length = 255)
	private String password;
	
	private boolean accountNonLocked = true;
	private boolean accountNonExpired = true;
	private boolean credentialNonExpired = true;
	private boolean enable = true;
	
	private LocalDate credentialsExpiryDate;
	private LocalDate accountExpiryDate;
	
	private String twoFactorSecret;
	private boolean isTwoFactorEnabled = false;
	private String signUpMethod;
	
	
//	@ToString.Exclude
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name="role_id", referencedColumnName = "role_id")
	private Role role;
	
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createDate;
	
	@UpdateTimestamp
	private LocalDateTime updatedate;

	public User() {}
	
	
	public User(@NotBlank @Size(max = 20) String username, @NotBlank @Email @Size(max = 60) String email) {
		super();
		this.username = username;
		this.email = email;
	}





	public User(@NotBlank @Size(max = 20) String username, @NotBlank @Email @Size(max = 60) String email,
			@Size(max = 120) String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
	}



	public User(long userId, @NotBlank @Size(max = 20) String username, @NotBlank @Email @Size(max = 60) String email,
			@Size(max = 120) String password, boolean accountNonLocked, boolean accountNonExpired,
			boolean credentialNonExpired, boolean enable, LocalDate credentialsExpiryDate, LocalDate accountExpiryDate,
			String twoFactorSecret, boolean isTwoFactorEnabled, String signUpMethod, Role role,
			LocalDateTime createDate, LocalDateTime updatedate) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.password = password;
		this.accountNonLocked = accountNonLocked;
		this.accountNonExpired = accountNonExpired;
		this.credentialNonExpired = credentialNonExpired;
		this.enable = enable;
		this.credentialsExpiryDate = credentialsExpiryDate;
		this.accountExpiryDate = accountExpiryDate;
		this.twoFactorSecret = twoFactorSecret;
		this.isTwoFactorEnabled = isTwoFactorEnabled;
		this.signUpMethod = signUpMethod;
		this.role = role;
		this.createDate = createDate;
		this.updatedate = updatedate;
	}


	public long getUserId() {
		return userId;
	}


	public void setUserId(long userId) {
		this.userId = userId;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}


	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}


	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}


	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}


	public boolean isCredentialNonExpired() {
		return credentialNonExpired;
	}


	public void setCredentialNonExpired(boolean credentialNonExpired) {
		this.credentialNonExpired = credentialNonExpired;
	}


	public boolean isEnable() {
		return enable;
	}


	public void setEnable(boolean enable) {
		this.enable = enable;
	}


	public LocalDate getCredentialsExpiryDate() {
		return credentialsExpiryDate;
	}


	public void setCredentialsExpiryDate(LocalDate credentialsExpiryDate) {
		this.credentialsExpiryDate = credentialsExpiryDate;
	}


	public LocalDate getAccountExpiryDate() {
		return accountExpiryDate;
	}


	public void setAccountExpiryDate(LocalDate accountExpiryDate) {
		this.accountExpiryDate = accountExpiryDate;
	}


	public String getTwoFactorSecret() {
		return twoFactorSecret;
	}


	public void setTwoFactorSecret(String twoFactorSecret) {
		this.twoFactorSecret = twoFactorSecret;
	}


	public boolean isTwoFactorEnabled() {
		return isTwoFactorEnabled;
	}


	public void setTwoFactorEnabled(boolean isTwoFactorEnabled) {
		this.isTwoFactorEnabled = isTwoFactorEnabled;
	}


	public String getSignUpMethod() {
		return signUpMethod;
	}


	public void setSignUpMethod(String signUpMethod) {
		this.signUpMethod = signUpMethod;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	public LocalDateTime getCreateDate() {
		return createDate;
	}


	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}


	public LocalDateTime getUpdatedate() {
		return updatedate;
	}


	public void setUpdatedate(LocalDateTime updatedate) {
		this.updatedate = updatedate;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", accountNonLocked=" + accountNonLocked + ", accountNonExpired=" + accountNonExpired
				+ ", credentialNonExpired=" + credentialNonExpired + ", enable=" + enable + ", credentialsExpiryDate="
				+ credentialsExpiryDate + ", accountExpiryDate=" + accountExpiryDate + ", twoFactorSecret="
				+ twoFactorSecret + ", isTwoFactorEnabled=" + isTwoFactorEnabled + ", signUpMethod=" + signUpMethod
				+ ", role=" + role + ", createDate=" + createDate + ", updatedate=" + updatedate + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(accountExpiryDate, accountNonExpired, accountNonLocked, createDate, credentialNonExpired,
				credentialsExpiryDate, email, enable, isTwoFactorEnabled, password, role, signUpMethod, twoFactorSecret,
				updatedate, userId, username);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(accountExpiryDate, other.accountExpiryDate)
				&& accountNonExpired == other.accountNonExpired && accountNonLocked == other.accountNonLocked
				&& Objects.equals(createDate, other.createDate) && credentialNonExpired == other.credentialNonExpired
				&& Objects.equals(credentialsExpiryDate, other.credentialsExpiryDate)
				&& Objects.equals(email, other.email) && enable == other.enable
				&& isTwoFactorEnabled == other.isTwoFactorEnabled && Objects.equals(password, other.password)
				&& Objects.equals(role, other.role) && Objects.equals(signUpMethod, other.signUpMethod)
				&& Objects.equals(twoFactorSecret, other.twoFactorSecret)
				&& Objects.equals(updatedate, other.updatedate) && userId == other.userId
				&& Objects.equals(username, other.username);
	}


	
	
	
	
}
