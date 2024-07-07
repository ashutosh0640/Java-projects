package com.InMemorySecurityProject_01.InMemorySecurityProject_01.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Students implements UserDetails{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="student_name")
	private String StudentName;
	
	@Column(name="phone_no", unique=true, nullable=false)
	private String phoneNo;
	
	@Column(unique=true, nullable=false)
	private String email;
	
	private String state;
	
	private String city;
	
	private String password;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roleList = new ArrayList<>();
	
	
	public Students() {
		super();
	}


	public Students(String name, String phoneNo, String email, String state, String city, String password) {
		super();
		this.StudentName = name;
		this.phoneNo = phoneNo;
		this.email = email;
		this.state = state;
		this.city = city;
		this.password = password;
	}


	public String getStudentName() {
		return StudentName;
	}


	public void setStudentName(String name) {
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
	
	


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "Students [id=" + id + ", name=" + StudentName + ", phoneNo=" + phoneNo + ", email=" + email + ", state="
				+ state + ", city=" + city + "]";
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<SimpleGrantedAuthority> roles = roleList.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
		return roles;
	}


	@Override
	public String getPassword() {
		return this.password;
	}


	@Override
	public String getUsername() {
		String usrName = this.email;
		return usrName;
	}
	
}
