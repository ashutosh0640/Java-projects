package com.luxora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.luxora.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query("SELECT u FROM User u WHERE u.email = :email")
	public <U extends User> U getUserByEmail(@Param("email") String email);
	
	@Query("SELECT u FROM User u WHERE u.mobileNumber = :mobile")
	public <U extends User> U getUserByMobileNumber(@Param("mobile") String mobile);

}
