package com.spring_security.jdbc.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Integer roleId;

	@Enumerated
	@Column(name = "role_name", length = 20)
	private AppRoles role;

//	@JsonBackRefrence
//	@ToString.Exclude
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<User> users = new HashSet<>();

	public Role() {}
	
	
	public Role(AppRoles role) {
		this.role = role;
	}


	public Role(Integer roleId, AppRoles role, Set<User> users) {
		this.roleId = roleId;
		this.role = role;
		this.users = users;
	}


	public Integer getRoleId() {
		return roleId;
	}


	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}


	public AppRoles getRole() {
		return role;
	}


	public void setRole(AppRoles role) {
		this.role = role;
	}


	public Set<User> getUsers() {
		return users;
	}


	public void setUsers(Set<User> users) {
		this.users = users;
	}


	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", role=" + role + ", users=" + users + "]";
	}

}
