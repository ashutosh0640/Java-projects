package com.luxora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.luxora.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("SELECT u FROM User u WHERE u.email = :email")
	public <U extends User> U getUserByEmail(@Param("email") String email);

	@Query("SELECT u FROM User u WHERE u.mobileNumber = :mobile")
	public <U extends User> U getUserByMobileNumber(@Param("mobile") String mobile);

	@Modifying
	@Query("UPDATE User u SET u.password = :password WHERE u.id = :id")
	public int updatePassword(@Param("id") Integer id, @Param("password") String password);

	@Query("SELECT u FROM User u WHERE " + "LOWER(u.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR "
			+ "LOWER(u.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR "
			+ "LOWER(u.mobile) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR "
			+ "LOWER(u.email) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
	List<User> searchByAnyField(@Param("searchTerm") String searchTerm);
}
