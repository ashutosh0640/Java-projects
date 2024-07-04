package com.InMemorySecurityProject_01.InMemorySecurityProject_01.service;

import java.util.List;
import java.util.Optional;

import com.InMemorySecurityProject_01.InMemorySecurityProject_01.entity.Students;

public interface StudentService {
	
	
	public Students save(Students student);
	
	public List<Students> saveAll(List<Students> students);
	
	public Optional<Students> findById(Long id);
	
	public List<Students> findAll();
	
	public void deleteById(Long id);
	
	public void delete(Students student);

	

}
