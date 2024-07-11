package com.spring_boot.crud_operation.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring_boot.crud_operation.entity.Student;
import com.spring_boot.crud_operation.service.StudentServiceImp;

@RestController
@RequestMapping("/api/student")
public class StudentController {
	
	@Autowired
	private StudentServiceImp service;
	
	@GetMapping("/test")
	public String test(@RequestParam String str) {
		return "This is test str: "+str;
	}
	
	
	@PostMapping("/saveAll")
	public ResponseEntity<List<Student>> saveAll(@RequestBody List<Student> students) {
		List<Student> s = service.saveAll(students);
		return new ResponseEntity<>(s, HttpStatus.CREATED);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Student> save(@RequestBody Student student) {
		Student s = service.save(student);
		return new ResponseEntity<>(s, HttpStatus.CREATED);
	}
	
	@GetMapping("/findById")
	public ResponseEntity<Optional<Student>> findById(@RequestParam long id) {
		Optional<Student> student = service.findById(id);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}
	
	@GetMapping("/existsById")
	public ResponseEntity<Boolean> existsById(@RequestParam long id) {
		boolean res = service.existsById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Student>> findAll() {
		List<Student> students = service.findAll();
		return new ResponseEntity<>(students, HttpStatus.OK);
 	}
	
	@GetMapping("/findAllById")
	public ResponseEntity<List<Student>> findAllById(@RequestBody List<Long> ids) {
		List<Student> students = service.findAllById(ids);
		return new ResponseEntity<>(students, HttpStatus.OK);
	}
	
	@GetMapping("/count")
	public ResponseEntity<Long> count() {
		long count = service.count();
		return new ResponseEntity<>(count, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteById")
	public boolean deleteById(@RequestParam long id) {
		boolean res = service.deleteById(id);
		return res;
	}
	
	@DeleteMapping("/delete")
	public boolean delete(@RequestBody Student student) {
		boolean res = service.delete(student);
		return res;
	}
	
	@DeleteMapping("/deleteAllById")
	public void deleteAllById(@RequestBody List<Long> id) {
		service.deleteAllById(id);
	}
	
	@DeleteMapping("/deleteAllStudents")
	public void deleteAll(@RequestBody List<Student> students) {
		service.deleteAll();
	}
	
	@DeleteMapping("/deleteAll")
	public void deleteAll() {
		service.deleteAll();
	}
	
	
	
	
	//PagingAndSorting Methods
	
	@GetMapping("/findAllBySort")
	public List<Student> findAll(@RequestParam String sortBy, @RequestParam String order) {
		Direction dir = Direction.ASC;
		if(order.equals("DESC")) {
			dir = Direction.DESC;
		}
		return service.findAll(Sort.by(dir, sortBy));
	}
	
	@GetMapping("/findAllByPage")
	public Page<Student> findAll(@RequestParam int pageNo, @RequestParam int size) {
		Pageable pageable = PageRequest.of(pageNo, size);
		return service.findAll(pageable);
	}

}
