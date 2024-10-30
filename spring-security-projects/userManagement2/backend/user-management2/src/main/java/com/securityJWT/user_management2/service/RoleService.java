package com.securityJWT.user_management2.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.securityJWT.user_management2.entity.Role;
import com.securityJWT.user_management2.entity.Users;
import com.securityJWT.user_management2.exceptions.RoleNotFoundException;
import com.securityJWT.user_management2.exceptions.UserNotFoundException;
import com.securityJWT.user_management2.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepo;
	
	public <S extends Role> List<S> saveAll(Iterable<S> Role) {
		List<S> userList = roleRepo.saveAll(Role);
		return userList;
	}

	
	public List<Role> findAll() {
		List<Role> userList = roleRepo.findAll();
		return userList;
	}

	
	public List<Role> findAllById(Iterable<Integer> ids) {
		List<Role> userList = roleRepo.findAllById(ids);
		return userList;
	}

	
	public <S extends Role> S save(S user) {
		S newUser = roleRepo.save(user);
		return newUser;
	}

	
	public Role findById(Integer id) {
		return roleRepo.findById(id)
				.orElseThrow(()->new RoleNotFoundException("Role by ID: "+id+" not found."));
	}
	
	
	public boolean existsById(int id) {
		boolean isExist = roleRepo.existsById(id);
		return isExist;
	}

	
	public long count() {
		long count = roleRepo.count();
		return count;
	}

	
	public void deleteById(int id) {
		roleRepo.deleteById(id);
	}

	
	public void delete(Role user) {
		roleRepo.delete(user);
	}

	
	public void deleteAllById(Iterable<? extends Integer> ids) {
		roleRepo.deleteAllById(ids);
	}

	
	public void deleteAll(Iterable<? extends Role> Role) {
		roleRepo.deleteAll();
	}

	
	public void deleteAll() {
		roleRepo.deleteAll();
		
	}

	
	public List<Role> findAll(String role, String direction) {
		Sort.Direction direc = Sort.Direction.ASC;
		
		if("DESC".equalsIgnoreCase(direction)) {
			direc = Sort.Direction.DESC;
		} else if (!"ASC".equalsIgnoreCase(direction) && !"DESC".equalsIgnoreCase(direction)) {
			throw new IllegalArgumentException("Invalid sort direction. Use 'ASC' and 'DESC'.");
		}
		
		Sort sort = Sort.by(direc, role);
		List<Role> roles =  roleRepo.findAll(sort);
		return roles;
	}

	
	public Page<Role> findAll(Sort sort) {
		
		return null;
	}

	
	public <S extends Role> Optional<S> findOne(Example<S> example) {
		
		return Optional.empty();
	}

	
	public <S extends Role> Page<S> findAll(Example<S> example, Pageable pageable) {
		
		return null;
	}

	
	public <S extends Role> int count(Example<S> example) {
		
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

	
	public Role getOne(int id) {
		
		return null;
	}

	
	public Role getById(int id) {
		
		return null;
	}

	
	public Role getReferenceById(int id) {
		
		return null;
	}

	
	public <S extends Role> List<S> findAll(Example<S> example) {
		
		return null;
	}

	
	public <S extends Role> List<S> findAll(Example<S> example, Sort sort) {
		
		return null;
	}

}
