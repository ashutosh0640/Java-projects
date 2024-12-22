package com.shop.luxora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shop.luxora.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	//public User findUserByEmail(String email);
	@Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.email = :email")
	User findUserByEmail(@Param("email") String email);
	
	@Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.mobileNo = :mobileNo")
	User findUserByMobileNo(String mobileNo);
}
