package com.reactSpring.fullCrud.entity;

import java.time.LocalDate;
import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(name="user_id", unique=true, nullable=false)
	private String userId;
	
	private String bio;
	
	@Column(nullable=false)
	private String password;
	
	private LocalDate dateOfbirth;
	
	private String city;
	
	@Lob
	private byte[] image;

	public User() {
		super();
	}
	
	

	public User(String userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
	}



	public User(String name, String userId, String password) {
		super();
		this.name = name;
		this.userId = userId;
		this.password = password;
	}




	public User(String name, String userId, LocalDate dateOfbirth, String city, byte[] image) {
		super();
		this.name = name;
		this.userId = userId;
		this.dateOfbirth = dateOfbirth;
		this.city = city;
		this.image = image;
	}


	public User(long id, String name, String userId, LocalDate dateOfbirth, String city, byte[] image) {
		super();
		this.id = id;
		this.name = name;
		this.userId = userId;
		this.dateOfbirth = dateOfbirth;
		this.city = city;
		this.image = image;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getDateOfbirth() {
		return dateOfbirth;
	}


	public void setDateOfbirth(LocalDate dateOfbirth) {
		this.dateOfbirth = dateOfbirth;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public byte[] getImage() {
		return image;
	}


	public void setImage(byte[] image) {
		this.image = image;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", userId=" + userId + ", bio=" + bio + ", dateOfbirth=" + dateOfbirth + ", city=" + city + ", image=" + Arrays.toString(image) + "]";
	}

	
	
	
}
