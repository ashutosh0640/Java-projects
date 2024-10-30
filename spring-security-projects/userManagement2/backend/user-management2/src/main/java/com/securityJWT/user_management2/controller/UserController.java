package com.securityJWT.user_management2.controller;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.securityJWT.user_management2.entity.Users;
import com.securityJWT.user_management2.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/view/{word}")
	public String getView(@PathVariable String word) {
		return "This is getView "+word;
	}
	
	@PostMapping("/saveAll")
	public ResponseEntity<List<Users>> saveAll(@RequestBody Iterable<Users> users) {
		List<Users> userList = userService.saveAll(users);
		return new ResponseEntity<>(userList, HttpStatus.ACCEPTED);
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Users>>  findAll() {
		List<Users> allUsers = userService.findAll();
		return new ResponseEntity<>(allUsers, HttpStatus.OK);
	}

	@GetMapping("/findAllById")
	public ResponseEntity<List<Users>>  findAllById(@RequestBody Iterable<Long> ids) {
		List<Users> allUsers = userService.findAllById(ids);
		return new ResponseEntity<>(allUsers, HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<Users> save(@RequestBody Users user) {
		Users savedUser =  userService.save(user);
		return new ResponseEntity<>(savedUser, HttpStatus.ACCEPTED);
	}

	@GetMapping("/findById")
	public ResponseEntity<Users> findById(@RequestParam Long id) {
		Users user = userService.findById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	
	public boolean existsById(Long id) {
		return false;
	}

	
	public long count() {
		return 0;
	}

	
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	
	public void delete(Users entity) {
		// TODO Auto-generated method stub
		
	}

	
	public void deleteAllById(Iterable<? extends Long> ids) {
		// TODO Auto-generated method stub
		
	}

	
	public void deleteAll(Iterable<? extends Users> entities) {
		// TODO Auto-generated method stub
		
	}

	
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	
	public List<Users> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Page<Users> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public <S extends Users> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	
	public <S extends Users> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public <S extends Users> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public <S extends Users> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public <S extends Users, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	
	public <S extends Users> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public <S extends Users> List<S> saveAllAndFlush(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void deleteAllInBatch(Iterable<Users> entities) {
		// TODO Auto-generated method stub
		
	}

	
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		
	}

	
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	
	public Users getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Users getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Users getReferenceById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public <S extends Users> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public <S extends Users> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

}
