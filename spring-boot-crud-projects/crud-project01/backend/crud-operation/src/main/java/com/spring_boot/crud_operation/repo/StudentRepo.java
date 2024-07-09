package com.spring_boot.crud_operation.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring_boot.crud_operation.entity.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long>{}
