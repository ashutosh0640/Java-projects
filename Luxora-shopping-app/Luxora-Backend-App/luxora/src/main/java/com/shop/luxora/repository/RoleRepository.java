package com.shop.luxora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.luxora.entity.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	
	public Role findByName(String name);

}
