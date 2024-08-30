package com.luxora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.luxora.entity.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer>{
	
	@Query("SELECT r FROM UserRole r WHERE r.name = :name")
	public UserRole findRoleByName(@Param("name") String name);

}
