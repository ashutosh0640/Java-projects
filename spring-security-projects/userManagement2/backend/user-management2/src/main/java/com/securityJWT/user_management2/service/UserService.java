package com.securityJWT.user_management2.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.securityJWT.user_management2.entity.Users;
import com.securityJWT.user_management2.exceptions.UserNotFoundException;
import com.securityJWT.user_management2.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	@Autowired
	private UserRepository userRepo;
	
	@Transactional
	public <S extends Users> List<S> saveAll(Iterable<S> users) {
		users.forEach(user->user.setPassword(passwordEncoder.encode(user.getPassword())));
		List<S> userList = userRepo.saveAll(users);
		return userList;
	}

	
	public List<Users> findAll() {
		List<Users> userList = userRepo.findAll();
		return userList;
	}

	
	public List<Users> findAllById(Iterable<Long> ids) {
		List<Users> userList = userRepo.findAllById(ids);
		return userList;
	}

	
	public <S extends Users> S save(S user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		System.out.println(user.toString());
		S newUser = userRepo.save(user);
		return newUser;
	}

	
	public Users findById(Long id) {
		System.out.println(id);
		return userRepo.findById(id)
				.orElseThrow(()->new UserNotFoundException("User by Id: "+id+" not found."));
	}

	
	public boolean existsById(Long id) {
		boolean isExist = userRepo.existsById(id);
		return isExist;
	}

	
	public long count() {
		long count = userRepo.count();
		return count;
	}

	
	public void deleteById(Long id) {
		userRepo.deleteById(id);
		
	}

	
	public void delete(Users user) {
		userRepo.delete(user);
	}

	
	public void deleteAllById(Iterable<? extends Long> ids) {
		userRepo.deleteAllById(ids);
	}

	
	public void deleteAll(Iterable<? extends Users> users) {
		userRepo.deleteAll();
	}

	
	public void deleteAll() {
		userRepo.deleteAll();
		
	}

	
	public List<Users> findAll(String... fields) {
		Sort sort = Sort.by(fields);
		List<Users> userList = userRepo.findAll(sort);
		return userList;
	}

	
	public Page<Users> findAll(Pageable pageable) {
		return null;
	}

	
	public <S extends Users> Optional<S> findOne(Example<S> example) {
		return Optional.empty();
	}

	
	public <S extends Users> Page<S> findAll(Example<S> example, Pageable pageable) {
		return null;
	}

	
	public <S extends Users> long count(Example<S> example) {
		return 0;
	}

	
	public <S extends Users> boolean exists(Example<S> example) {
		return false;
	}

	
	public <S extends Users, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return null;
	}

	
	public void flush() {
		
	}

	
	public <S extends Users> S saveAndFlush(S entity) {
		return null;
	}

	
	public <S extends Users> List<S> saveAllAndFlush(Iterable<S> entities) {
		return null;
	}

	
	public void deleteAllInBatch(Iterable<Users> entities) {
		
	}

	
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		
	}

	
	public void deleteAllInBatch() {
		
	}

	
	public Users getOne(Long id) {
		return null;
	}

	
	public Users getById(Long id) {
		return null;
	}

	
	public Users getReferenceById(Long id) {
		return null;
	}

	
	public <S extends Users> List<S> findAll(Example<S> example) {
		return null;
	}

	
	public <S extends Users> List<S> findAll(Example<S> example, Sort sort) {
		return null;
	}

}
