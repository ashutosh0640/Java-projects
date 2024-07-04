package com.InMemorySecurityProject_01.InMemorySecurityProject_01.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.InMemorySecurityProject_01.InMemorySecurityProject_01.entity.Students;
import com.InMemorySecurityProject_01.InMemorySecurityProject_01.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService service;
	
	@GetMapping("/test")
	public String test(@RequestParam String str) {
		return "You send "+str;
	}
	
	@PostMapping("/save")
	public ResponseEntity<Students> save(@RequestBody Students student) {
		Students s = service.save(student);
		return new ResponseEntity<>(s, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/saveAll")
	public ResponseEntity<List<Students>> saveAll(List<Students> students) {
		List<Students> s = service.saveAll(students);
		return new ResponseEntity<>(s, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/findById")
	public ResponseEntity<Optional<Students>> findById(@RequestParam Long id) {
		Optional<Students> s = service.findById(id);
		if (s.isPresent()) {
			return ResponseEntity.ok(s);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Students>> findAll() {
		List<Students> s = service.findAll();
		return new ResponseEntity<>(s, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteById")
	public String deleteById(@RequestParam Long id) {
		service.deleteById(id);
		return "deleted successfully";
	}
	
	@DeleteMapping("/delete")
	public String delete(@RequestBody Students student) {
		service.delete(student);
		return "deleted successfully";
	}
	

}
