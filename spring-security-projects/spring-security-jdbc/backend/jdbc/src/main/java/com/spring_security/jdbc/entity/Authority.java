package com.spring_security.jdbc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//@Entity
//@Table(name = "authorities")
public class Authority {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@Column(name = "authority", nullable = false, length = 50)
	private String authority;
	
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "username", nullable = false)
	private User user;


	public Authority() {
		super();
	}


	public Authority(Long id, String authority, User user) {
		super();
		this.id = id;
		this.authority = authority;
		this.user = user;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getAuthority() {
		return authority;
	}


	public void setAuthority(String authority) {
		this.authority = authority;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "Authority [id=" + id + ", authority=" + authority + ", user=" + user + "]";
	}
	

}
