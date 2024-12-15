package com.monitor.multipinger.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.monitor.multipinger.entity.LogEntity;

@Repository
public interface LogRepository extends MongoRepository<LogEntity, Integer>{

}
