package com.spring_security.InMemory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.spring_security.InMemory.entity.Student;



public interface StudentService {
	
	//CrudRepository Methods
	
	public Student save(Student student);
	
	public List<Student> saveAll(List<Student> students);
	
	public Optional<Student> findById(long id);
	
	public boolean existsById(long id);
	
	public List<Student> findAll();
	
	public List<Student> findAllById(List<Long> ids);
	
	public long count();
	
	public boolean deleteById(long id);
	
	public boolean delete(Student student);
	
	public void deleteAllById(List<Long> id);
	
	public void deleteAll(List<Student> students);
	
	public void deleteAll();
	
	
	
	
	//PagingAndSorting Methods
	
	public List<Student> findAll(Sort sort);
	
	public Page<Student> findAll(Pageable pageable);

}
