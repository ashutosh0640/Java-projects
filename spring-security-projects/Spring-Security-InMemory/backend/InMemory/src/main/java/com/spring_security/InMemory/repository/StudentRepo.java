package com.spring_security.InMemory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring_security.InMemory.entity.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long>{}
