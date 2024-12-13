package com.shop.luxora.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(length=40, nullable=false)
	@NotBlank(message="First Name is mandatory")
	private String firstName;
	
	@Column(length=40)
	private String middleName;
	
	@Column(length=40)
	private String lastName;

	@Column(length=40, nullable=false, unique=true)
	@Email(message = "Email should be valid")
	@NotBlank(message = "Email is mandatory")
	private String email;
	
	@Column(length=40, nullable=false, unique=true)
	@Pattern(regexp = "^[6789]\\d{9}$", message = "Invalid mobile number")
	private String mobileNo;
	
	@Column(nullable=false)
	@Size(min = 7, max = 40, message = "Password must be between 7 and 40 characters.")
	@NotBlank(message = "Password is mandatory")
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,30}$", message = "Invalid password. Password should contain at least one capital letter one small letter and one special character(@ $ ! % * ? &). Length should be between 6 and 40")
	private String password;
	
	private String image;
	
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
    @JoinTable(name = "user_roles",
               joinColumns = @JoinColumn(name = "user_id"),
               inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
	
	
	public User() {}


	public User(String firstName, String middleName, String lastName, String email, String mobileNo,
			@Size(min = 7, max = 40) String password, String image, Set<Role> roles) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNo = mobileNo;
		this.password = password;
		this.image = image;
		this.roles = roles;
	}
	

	public User(long id, String firstName, String middleName, String lastName, String email, String mobileNo,
			@Size(min = 7, max = 40) String password, String image, Set<Role> roles) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNo = mobileNo;
		this.password = password;
		this.image = image;
		this.roles = roles;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getMiddleName() {
		return middleName;
	}


	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMobileNo() {
		return mobileNo;
	}


	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public void addRole(Role role) {
		this.roles.add(role);
	}
	
	public void removeRole(Role role) {
        this.roles.remove(role);
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
				+ ", email=" + email + ", mobileNo=" + mobileNo + ", password=" + password + ", image=" + image
				+ ", roles=" + roles + "]";
	}
	
}
