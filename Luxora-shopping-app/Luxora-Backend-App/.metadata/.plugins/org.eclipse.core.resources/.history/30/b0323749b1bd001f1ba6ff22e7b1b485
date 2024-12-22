package com.shop.luxora.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.shop.luxora.entity.Role;


public interface RoleService {

	public List<Role> saveAll(List<Role> roles);

	public List<Role> findAll();
	
	public List<Role> findAllById(Iterable<Integer> ids);
	
	public Role save(Role role);
	
	public Role findById(Integer id);
	
	public boolean existsById(Integer id);
	
	public long getRolesCount();
	
	public void deleteById(Integer roleId);
	
	public void deleteRole(Role role);
	
	public void deleteAllByIds(List<Integer> ids);
	
	public void deleteAll(List<Role> roles);
	
	public void deleteAll();
	
	public List<Role> findAll(Sort sort);
	
	public Page<Role> findAll(Pageable pageable);
	
	public <S extends Role> Optional<S> findOne(Example<S> example);

}
