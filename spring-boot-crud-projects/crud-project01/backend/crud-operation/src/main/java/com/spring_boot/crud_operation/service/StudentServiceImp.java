package com.spring_boot.crud_operation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.spring_boot.crud_operation.entity.Student;
import com.spring_boot.crud_operation.exception.ResourceNotFoundException;
import com.spring_boot.crud_operation.repo.StudentRepo;

@Service
public class StudentServiceImp implements StudentService{
	
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
		repo.deleteAllById(id);
	}

	@Override
	public void deleteAll(List<Student> students) {
		repo.deleteAll(students);
	}

	@Override
	public void deleteAll() {
		repo.deleteAll();
		
	}

	@Override
	public List<Student> findAll(Sort sort) {
		return repo.findAll(sort);
	}


	@Override
	public Page<Student> findAll(Pageable pageable) {
		Page<Student> students =  repo.findAll(pageable);
		return students;
	}

}
