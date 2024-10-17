package com.security.user_management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.user_management.entity.Roles;

public interface RoleRepository extends JpaRepository<Roles, Integer>{
	
	//Optional<Roles> findByRole();

}
