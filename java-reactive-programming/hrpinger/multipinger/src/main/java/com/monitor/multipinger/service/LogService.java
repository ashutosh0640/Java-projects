package com.monitor.multipinger.service;

import java.util.List;
import java.util.Optional;

import com.monitor.multipinger.entity.LogEntity;

public interface LogService {
	
	public <S extends LogEntity> List<S> saveAll(Iterable<S> logEntities);
	
	public List<LogEntity> findAll();
	
	public List<LogEntity> findAllById(Iterable<Integer> ids);
	
	public <S extends LogEntity> S save(S logEntity);
	
	public Optional<LogEntity> findById(Integer id);
	
	public boolean existsById(Integer id);
	
	public long count();
	
	public void deleteById(Integer id);
	
	public void delete(LogEntity logEntity);
	
	public void deleteAllById(Iterable<? extends Integer> ids);
	
	public void deleteAll(Iterable<? extends LogEntity> logEntities);
	
	public void deleteAll();

}
