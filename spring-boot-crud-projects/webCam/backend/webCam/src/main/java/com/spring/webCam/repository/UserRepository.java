package com.spring.webCam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.webCam.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{}
