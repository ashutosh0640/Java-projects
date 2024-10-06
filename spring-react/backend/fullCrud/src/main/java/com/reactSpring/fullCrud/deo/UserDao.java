package com.reactSpring.fullCrud.deo;

import java.time.LocalDate;

public class UserDao {

	private long id;

	private String name;

	private String userId;

	private String bio;

	private LocalDate dateOfbirth;

	private String city;

	private String image;

	public UserDao() {
		super();
	}

	public UserDao(String name, String userId, String bio, LocalDate dateOfbirth, String city, String image) {
		super();
		this.name = name;
		this.userId = userId;
		this.bio = bio;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "UserDeo [id=" + id + ", name=" + name + ", userId=" + userId + ", bio=" + bio + ", dateOfbirth="
				+ dateOfbirth + ", city=" + city + ", image=" + image + "]";
	}

}
