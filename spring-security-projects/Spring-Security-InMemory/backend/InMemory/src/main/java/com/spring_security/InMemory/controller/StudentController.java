package com.spring_security.InMemory.controller;

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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring_security.InMemory.entity.Student;
import com.spring_security.InMemory.exception.ResourceNotFoundException;
import com.spring_security.InMemory.service.StudentService;

@RestController
@RequestMapping("/api/student")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class StudentController {

	@Autowired
	private StudentService service;
	
	@GetMapping("/test")
	public String test(@RequestParam String word) {
		return "This is test "+word;
	}

	@PostMapping("/save")
	public ResponseEntity<Student> save(@RequestBody Student student) {
		Student stud = service.save(student);
		return new ResponseEntity<>(stud, HttpStatus.ACCEPTED);
	}

	@PostMapping("/saveAll")
	public List<Student> saveAll(@RequestBody List<Student> students) {
		List<Student> s = service.saveAll(students);
		return s;
	}

	@GetMapping("/find/Id")
	public ResponseEntity<Optional<Student>> findById(@RequestParam long id) {
		Optional<Student> student = service.findById(id);
		if (student.isEmpty()) {
			throw new ResourceNotFoundException("Student is not found by this id: " + id);
		}
		return new ResponseEntity<>(student,HttpStatus.OK );
	}

	@GetMapping("/exist/Id")
	public boolean existsById(@RequestParam long id) {
		return service.existsById(id);
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Student>> findAll() {
		List<Student> student = service.findAll();
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	@GetMapping("/findAll/Id")
	public List<Student> findAllById(@RequestBody List<Long> ids) {
		return service.findAllById(ids);
	}

	@GetMapping("/count")
	public long count() {
		return service.count();
	}

	@DeleteMapping("/delete/Id")
	public boolean deleteById(long id) {
		boolean res = service.existsById(id);
		if (res) {
			service.deleteById(id);
		}
		return res;
	}

	@DeleteMapping("/delete")
	public boolean delete(@RequestParam Student student) {
		boolean res = service.existsById(student.getId());
		if (res) {
			service.delete(student);
		}
		return res;
	}

	@DeleteMapping("/deleteAll/Id")
	public void deleteAllById(@RequestBody List<Long> id) {
		service.deleteAllById(id);
	}

	@DeleteMapping("/deleteAll/student")
	public void deleteAll(@RequestBody List<Student> students) {
		service.deleteAll(students);
	}

	@DeleteMapping("/deleteAll")
	public void deleteAll() {
		service.deleteAll();

	}

	@GetMapping("/findAll/sort")
	public ResponseEntity<List<Student>> findAll(String sortBy, String direction) {
		Direction dir = Direction.ASC;
		if (direction.equals("DESC")) {
			dir = Direction.DESC;
		}
		List<Student> students = service.findAll(Sort.by(dir, sortBy));
		return new ResponseEntity<>(students, HttpStatus.OK);
	}

	@GetMapping("/findAll/page")
	public ResponseEntity<Page<Student>> findAll(int pageNo, int size) {
		Pageable pageable = PageRequest.of(pageNo, size);
		Page<Student> students = service.findAll(pageable);
		return new ResponseEntity<>(students, HttpStatus.OK);
	}

}
