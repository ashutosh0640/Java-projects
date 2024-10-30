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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.securityJWT.user_management2.entity.Role;
import com.securityJWT.user_management2.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	
	@PostMapping("/saveAll")
	public ResponseEntity<List<Role>> saveAll(@RequestBody Iterable<Role> roles) {
		List<Role> roleList = roleService.saveAll(roles);
		return new ResponseEntity<>(roleList, HttpStatus.OK);
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Role>> findAll() {
		List<Role> allRoles = roleService.findAll();
		return new ResponseEntity<>(allRoles, HttpStatus.OK);
	}

	@GetMapping("/findAllById")
	public ResponseEntity<List<Role>> findAllById(@RequestBody Iterable<Integer> ids) {
		List<Role> roleList = roleService.findAllById(ids);
		return new ResponseEntity<>(roleList, HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<Role> save(@RequestBody Role role) {
		System.out.println("role "+role.toString());
		Role savedRole = roleService.save(role);
		return new ResponseEntity<>(savedRole, HttpStatus.OK);
	}

	@GetMapping("/findById")
	public ResponseEntity<Role> findById(@RequestParam Integer id) {
		Role role = roleService.findById(id);
		return new ResponseEntity<>(role, HttpStatus.OK);
	}

	@GetMapping("/existsById")
	public ResponseEntity<Boolean> existsById(@RequestParam Integer id) {
		boolean exist = roleService.existsById(id);
		return new ResponseEntity<>(exist, HttpStatus.OK);
	}

	@GetMapping("/count")
	public ResponseEntity<Long> count() {
		long count = roleService.count();
		return new ResponseEntity<>(count, HttpStatus.OK);
	}

	@DeleteMapping("/deleteById")
	public void deleteById(@RequestParam Integer id) {
		roleService.deleteById(id);
	}

	@DeleteMapping("/delete")
	public void delete(@RequestBody Role role) {
		roleService.delete(role);
	}

	@DeleteMapping("/deleteAllById")
	public void deleteAllById(@RequestBody Iterable<? extends Integer> ids) {
		roleService.deleteAllById(ids);
	}

	@DeleteMapping("/deleteAllByRole")
	public void deleteAll(@RequestBody Iterable<? extends Role> roles) {
		roleService.deleteAll(roles);
	}

	@DeleteMapping("/deleteAll")
	public void deleteAll() {
		roleService.deleteAll();
	}

	
	public List<Role> findAll(Sort sort) {
		
		return null;
	}

	
	public Page<Role> findAll(Pageable pageable) {
		
		return null;
	}

	
	public <S extends Role> Optional<S> findOne(Example<S> example) {
		
		return Optional.empty();
	}

	
	public <S extends Role> Page<S> findAll(Example<S> example, Pageable pageable) {
		
		return null;
	}

	
	public <S extends Role> long count(Example<S> example) {
		
		return 0;
	}

	
	public <S extends Role> boolean exists(Example<S> example) {
		
		return false;
	}

	
	public <S extends Role, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		
		return null;
	}

	
	public void flush() {
		
		
	}

	
	public <S extends Role> S saveAndFlush(S entity) {
		
		return null;
	}

	
	public <S extends Role> List<S> saveAllAndFlush(Iterable<S> entities) {
		
		return null;
	}

	
	public void deleteAllInBatch(Iterable<Role> entities) {
		
		
	}

	
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		
		
	}

	
	public void deleteAllInBatch() {
		
		
	}

	
	public Role getOne(Integer id) {
		
		return null;
	}

	
	public Role getById(Integer id) {
		
		return null;
	}

	
	public Role getReferenceById(Integer id) {
		
		return null;
	}

	
	public <S extends Role> List<S> findAll(Example<S> example) {
		
		return null;
	}

	
	public <S extends Role> List<S> findAll(Example<S> example, Sort sort) {
		
		return null;
	}

}
