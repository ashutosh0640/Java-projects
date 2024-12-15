package com.monitor.multipinger.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.monitor.multipinger.entity.LogEntity;
import com.monitor.multipinger.repository.LogRepository;

@Service
public class LogServiceImp implements LogService {
	
	private LogRepository logRepository;

	public LogServiceImp(LogRepository logRepository) {
		super();
		this.logRepository = logRepository;
	}
	
	
    @Override
    public <S extends LogEntity> List<S> saveAll(Iterable<S> logEntities) {
        return logRepository.saveAll(logEntities);
    }

    @Override
    public List<LogEntity> findAll() {
        return logRepository.findAll();
    }

    @Override
    public List<LogEntity> findAllById(Iterable<Integer> ids) {
        return logRepository.findAllById(ids);
    }

    @Override
    public <S extends LogEntity> S save(S logEntity) {
        return logRepository.save(logEntity);
    }

    @Override
    public Optional<LogEntity> findById(Integer id) {
        return logRepository.findById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return logRepository.existsById(id);
    }

    @Override
    public long count() {
        return logRepository.count();
    }

    @Override
    public void deleteById(Integer id) {
        logRepository.deleteById(id);
    }

    @Override
    public void delete(LogEntity logEntity) {
        logRepository.delete(logEntity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> ids) {
        logRepository.deleteAllById(ids);
    }

    @Override
    public void deleteAll(Iterable<? extends LogEntity> logEntities) {
        logRepository.deleteAll(logEntities);
    }

    @Override
    public void deleteAll() {
        logRepository.deleteAll();
    }

//	@Override
//	public <S extends LogEntity> List<S> saveAll(Iterable<S> logEntities) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<LogEntity> findAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<LogEntity> findAllById(Iterable<Integer> ids) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends LogEntity> S save(S logEntity) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Optional<LogEntity> findById(Integer id) {
//		// TODO Auto-generated method stub
//		return Optional.empty();
//	}
//
//	@Override
//	public boolean existsById(Integer id) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public long count() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public void deleteById(Integer id) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void delete(LogEntity logEntity) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void deleteAllById(Iterable<? extends Integer> ids) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void deleteAll(Iterable<? extends LogEntity> logEntities) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void deleteAll() {
//		// TODO Auto-generated method stub
//
//	}

}
