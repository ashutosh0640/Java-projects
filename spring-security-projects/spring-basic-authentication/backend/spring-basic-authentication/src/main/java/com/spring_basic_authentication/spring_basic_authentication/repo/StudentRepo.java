package com.spring_basic_authentication.spring_basic_authentication.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring_basic_authentication.spring_basic_authentication.entity.Student;

public interface StudentRepo extends JpaRepository<Student, String>{}
