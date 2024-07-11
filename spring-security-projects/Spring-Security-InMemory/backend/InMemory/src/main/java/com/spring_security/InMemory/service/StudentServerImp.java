package com.spring_security.InMemory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.spring_security.InMemory.entity.Student;
import com.spring_security.InMemory.exception.ResourceNotFoundException;
import com.spring_security.InMemory.repository.StudentRepo;


@Service
public class StudentServerImp implements StudentService{
	
	@Autowired
	private StudentRepo repo;
	@Override
	public Student save(Student student) {
		return repo.save(student);
	}

	@Override
	public List<Student> saveAll(List<Student> students) {
		List<Student> s = repo.saveAll(students);
		return s;
	}

	@Override
	public Optional<Student> findById(long id) {
		Optional<Student> student =  repo.findById(id);
		if(student.isEmpty()) {
			throw new ResourceNotFoundException("Student is not found by this id: "+id);
		}
		return student;
	}

	@Override
	public boolean existsById(long id) {
		return repo.existsById(id);
	}

	@Override
	public List<Student> findAll() {
		return repo.findAll();
	}

	@Override
	public List<Student> findAllById(List<Long> ids) {
		return repo.findAllById(ids);
	}

	@Override
	public long count() {
		return repo.count();
	}

	@Override
	public boolean deleteById(long id) {
		boolean res = repo.existsById(id);
		if(res) {
			repo.deleteById(id);
		}
		return res;
	}

	@Override
	public boolean delete(Student student) {
		boolean res = repo.existsById(student.getId());
		if(res) {
			repo.delete(student);
		}
		return res;
	}

	@Override
	public void deleteAllById(List<Long> id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(List<Student> students) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		repo.deleteAll();
		
	}

	@Override
	public List<Student> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
