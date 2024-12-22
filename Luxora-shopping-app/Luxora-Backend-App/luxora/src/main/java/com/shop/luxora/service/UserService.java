package com.shop.luxora.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.shop.luxora.entity.User;

public interface UserService {
	
	
	public List<User> saveAll(List<User> userlist);

	
	public List<User> findAll();

	
	public List<User> findAllById(List<Long> ids);
	
	public User findUserByEmail(String email);
	
	public User findUserByMobileNo(String mobileNo);
	
	public User save(User user);

	
	public User findById(Long id);

	
	public boolean existsById(Long id);

	
	public long count();

	
	public void deleteById(Long id);

	
	public void delete(User user);

	
	public void deleteAllById(List<? extends Long> ids);

	
	public void deleteAll(List<? extends User> entities);

	
	public void deleteAll();

	
	public List<User> findAll(Sort sort);

	
	public Page<User> findAll(Pageable pageable);

	
	public <S extends User> Optional<S> findOne(Example<S> example);

	
	public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable);

	
	public <S extends User> long count(Example<S> example);

	
	public <S extends User> boolean exists(Example<S> example);

	
	public <S extends User, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	
	public void flush();

	
	public <S extends User> S saveAndFlush(S entity);

	
	public <S extends User> List<S> saveAllAndFlush(List<S> entities);

	
	public void deleteAllInBatch(List<User> entities);

	
	public void deleteAllByIdInBatch(List<Long> ids);

	
	public void deleteAllInBatch();

	
	public User getOne(Long id);

	
	public User getById(Long id);

	
	public User getReferenceById(Long id);

	
	public List<User> findAll(Example<User> example);

	
	public List<User> findAll(Example<User> example, Sort sort);


}
