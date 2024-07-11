package com.spring_security.InMemory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Entity
@Table(name="student", indexes= {
		@Index(name="index_email", columnList="email"),
		@Index(name="index_mobile", columnList="mobile")
})
public class Student {
	
	@Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private long id;
	 
	 @Column(name="first_name")
	 private String firstName;
	 
	 @Column(name="last_name")
	 private String lastName;
	 
	 @Column(name="email")
	 private String email;
	 
	 private String mobile;
	 
	 @Column(name="profile_pic")
	 private String profilePic;
	 
	 private String city;
	 
	 private String state;
	 
	 private String zipcode;
	 
	 private String country;

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
