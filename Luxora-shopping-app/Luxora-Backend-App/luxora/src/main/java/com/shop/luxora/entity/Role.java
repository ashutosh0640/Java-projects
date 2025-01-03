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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="roles")
public class Role {
	
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
	@SequenceGenerator(name = "role_seq", sequenceName = "role_sequence", allocationSize = 10)
	private Integer id;
	
	@Column(length=40, nullable=false, unique=true)
	@NotBlank(message="Role name cann't be blank.")
	private String name;
	
	@Column(length=255, nullable=false)
	@NotBlank(message="Role discription cann't be blank.")
	private String discription;
	

	
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();

	public Role() {}

	public Role(String name, String discription) {
		this.name = name;
		this.discription = discription;
	}



	public Role(Integer id, String name, String discription) {
		this.id = id;
		this.name = name;
		this.discription = discription;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}
	
}
