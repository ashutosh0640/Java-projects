package com.InMemorySecurityProject_01.InMemorySecurityProject_01.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.InMemorySecurityProject_01.InMemorySecurityProject_01.entity.Students;
import com.InMemorySecurityProject_01.InMemorySecurityProject_01.repo.StudentRepo;

@Service
public class StudentServiceImp implements StudentService{
	
	@Autowired
	private StudentRepo repo;

	@Override
	public Students save(Students student) {
		return repo.save(student);
	}

	@Override
	public List<Students> saveAll(List<Students> students) {
		return repo.saveAll(students);
	}

	@Override
	public Optional<Students> findById(Long id) {
		return repo.findById(id);
	}

	@Override
	public List<Students> findAll() {
		return repo.findAll();
	}

	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
		
	}

	@Override
	public void delete(Students student) {
		repo.delete(student);
	}

}
