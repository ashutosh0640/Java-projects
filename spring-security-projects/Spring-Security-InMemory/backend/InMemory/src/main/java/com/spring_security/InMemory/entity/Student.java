package com.spring_security.InMemory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Entity
@Table(name = "student", indexes = { 
		@Index(name = "index_email", columnList = "email"),
		@Index(name = "index_mobile", columnList = "mobile") })
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "first_name", nullable=false)
	private String firstName;

	@Column(name = "last_name", nullable=false)
	private String lastName;

	@Column(name = "email", nullable=false, unique=true)
	private String email;

	@Column(name="mobile", nullable=false, unique=true)
	private String mobile;

	
	@Column(name="gender", nullable=false)
	private String gender;


	@Column(nullable=false)
	private String state;

	@Column(nullable=false)
	private String country;
	
	
	

	public Student() {
		super();
	}

	public Student(String firstName, String lastName, String email, String mobile, String gender, String state,
			String country) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.gender = gender;
		this.state = state;
		this.country = country;
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


	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
