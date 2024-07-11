package com.spring_basic_authentication.spring_basic_authentication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.spring_boot.crud_operation.entity.Student;
import com.spring_boot.crud_operation.exception.ResourceNotFoundException;
import com.spring_boot.crud_operation.repo.StudentRepo;

public class StudentServiceImp implements StudentService{
	

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
	public Optional<Student> findById(String id) {
		Optional<Student> student =  repo.findById(id);
		if(student.isEmpty()) {
			throw new ResourceNotFoundException("Student is not found by id: "+id);
		}
		return student;
	}

	@Override
	public boolean existsById(String id) {
		return repo.existsById(id);
	}

	@Override
	public List<Student> findAll() {
		return repo.findAll();
	}

	@Override
	public List<Student> findAllById(List<String> ids) {
		return repo.findAllById(ids);
	}

	@Override
	public long count() {
		return repo.count();
	}

	@Override
	public boolean deleteById(String id) {
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
	public void deleteAllById(List<String> id) {
		repo.deleteAllById(id);
	}

	@Override
	public void deleteAll(List<Student> students) {
		repo.deleteAll();
	}

	@Override
	public void deleteAll() {
		repo.deleteAll();
	}

	@Override
	public List<Student> findAll(Sort sort) {
		List<Student> students = repo.findAll(sort);
		if (students.isEmpty()) {
			throw new ResourceNotFoundException("No student found");
		}
		return students;
	}

	@Override
	public Page<Student> findAll(Pageable pageable) {
		Page<Student> students =  repo.findAll(pageable);
		return students;
	}

}
