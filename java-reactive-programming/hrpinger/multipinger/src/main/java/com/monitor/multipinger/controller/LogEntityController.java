package com.monitor.multipinger.controller;

import java.util.List;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monitor.multipinger.entity.LogEntity;
import com.monitor.multipinger.service.LogServiceImp;

@RestController
@RequestMapping("/logs")
public class LogEntityController {

	private LogServiceImp logEntityService;

	public LogEntityController(LogServiceImp logService) {
		super();
		this.logEntityService = logService;
	}

	@PostMapping
	public ResponseEntity<?> saveLog(@RequestBody LogEntity logEntity) {
		try {
			LogEntity savedLog = logEntityService.save(logEntity);
			return new ResponseEntity<>(savedLog, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Error saving log: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Save multiple logs
	@PostMapping("/batch")
	public ResponseEntity<?> saveLogs(@RequestBody List<LogEntity> logEntities) {
		try {
			List<LogEntity> savedLogs = logEntityService.saveAll(logEntities);
			return new ResponseEntity<>(savedLogs, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Error saving logs: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Get all logs
	@GetMapping
	public ResponseEntity<?> getAllLogs() {
		try {
			List<LogEntity> logs = logEntityService.findAll();
			return new ResponseEntity<>(logs, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error retrieving logs: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Get log by ID
	@GetMapping("/{id}")
	public ResponseEntity<?> getLogById(@PathVariable Integer id) {
		try {
			Optional<LogEntity> log = logEntityService.findById(id);
			if (log.isPresent()) {
				return new ResponseEntity<>(log.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Log not found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Error retrieving log: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Delete log by ID
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteLogById(@PathVariable Integer id) {
		try {
			if (logEntityService.existsById(id)) {
				logEntityService.deleteById(id);
				return new ResponseEntity<>("Log deleted successfully", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Log not found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Error deleting log: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Count all logs
	@GetMapping("/count")
	public ResponseEntity<?> countLogs() {
		try {
			long count = logEntityService.count();
			return new ResponseEntity<>("Total logs: " + count, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error counting logs: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Delete all logs
	@DeleteMapping("/deleteAll")
	public ResponseEntity<?> deleteAllLogs() {
		try {
			logEntityService.deleteAll();
			return new ResponseEntity<>("All logs deleted successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error deleting logs: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
