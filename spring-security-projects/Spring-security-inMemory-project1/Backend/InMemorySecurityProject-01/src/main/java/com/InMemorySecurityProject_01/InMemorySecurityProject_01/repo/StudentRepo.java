package com.InMemorySecurityProject_01.InMemorySecurityProject_01.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.InMemorySecurityProject_01.InMemorySecurityProject_01.entity.Students;

@Repository
public interface StudentRepo extends JpaRepository<Students, Long>{}
