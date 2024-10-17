package com.spring_security.jdbc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring_security.jdbc.entity.AppRoles;
import com.spring_security.jdbc.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	public Optional<Role> findByRole(AppRoles role);
}
