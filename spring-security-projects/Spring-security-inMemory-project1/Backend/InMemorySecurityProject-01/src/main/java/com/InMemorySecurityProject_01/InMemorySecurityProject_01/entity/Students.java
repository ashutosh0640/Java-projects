package com.InMemorySecurityProject_01.InMemorySecurityProject_01.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Students {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="student_name")
	private String StudentName;
	
	@Column(name="phone_no")
	private String phoneNo;
	
	private String email;
	
	private String state;
	
	private String city;
	
	
	public Students() {
		super();
	}


	public Students(String name, String phoneNo, String email, String state, String city) {
		super();
		this.StudentName = name;
		this.phoneNo = phoneNo;
		this.email = email;
		this.state = state;
		this.city = city;
	}


	public String getName() {
		return StudentName;
	}


	public void setName(String name) {
		this.StudentName = name;
	}


	public String getPhoneNo() {
		return phoneNo;
	}


	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	@Override
	public String toString() {
		return "Students [id=" + id + ", name=" + StudentName + ", phoneNo=" + phoneNo + ", email=" + email + ", state="
				+ state + ", city=" + city + "]";
	}
	
}
