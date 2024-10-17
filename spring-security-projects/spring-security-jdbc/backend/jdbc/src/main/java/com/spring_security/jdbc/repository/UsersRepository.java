package com.spring_security.jdbc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring_security.jdbc.entity.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String name);
}
