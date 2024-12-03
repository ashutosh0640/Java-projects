package com.api.api_testing.controller;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.api_testing.dto.CourseDTO;
import com.api.api_testing.entity.Course;
import com.api.api_testing.service.CourseService;


@RestController
@RequestMapping("/course")
public class CourseController {
	
	private final CourseService courseService;
	
	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}


	@PostMapping("/saveAll")
	public List<CourseDTO> saveAll(@RequestBody Iterable<Course> courses) {
		List<CourseDTO> courseList = courseService.saveAll(courses);
		return courseList;
	}

	@GetMapping("/findAll")
	public List<CourseDTO> findAll() {
		return courseService.findAll();
	}

	@GetMapping("/findAllByIds")
	public List<CourseDTO> findAllById(@RequestBody Iterable<Integer> ids) {
		return courseService.findAllById(ids);
	}

	@PostMapping("/save")
	public CourseDTO save(@RequestBody Course course) {
		return courseService.save(course);
	}

	@GetMapping("/findById")
	public CourseDTO findById(@RequestParam Integer id) {
		return courseService.findById(id);
	}

	@GetMapping("/exists")
	public boolean existsById(@RequestParam Integer id) {
		return courseService.existsById(id);
	}

	@GetMapping("/count")
	public long count() {
		return courseService.count();
	}

	@DeleteMapping("/deleteById")
	public void deleteById(@RequestParam Integer id) {
		courseService.deleteById(id);
		
	}

	@DeleteMapping("/delete")
	public void delete(@RequestBody Course Course) {
		courseService.delete(Course);
	}

	@DeleteMapping("/deleteAllByIds")
	public void deleteAllById(@RequestBody Iterable<? extends Integer> ids) {
		courseService.deleteAllById(ids);
	}

	@DeleteMapping("/deleteAllByCourse")
	public void deleteAll(@RequestBody Iterable<? extends Course> Courses) {
		courseService.deleteAll(Courses);
	}

	@DeleteMapping("/deleteAll")
	public void deleteAll() {
		courseService.deleteAll();
	}

	
	public List<Course> findAll(String direction, String... CourseName) {
		return courseService.findAll(direction, CourseName);
	}

	
	public Page<Course> findAll(int pageNumber, int pageSize) {
		return courseService.findAll(pageNumber, pageSize);
	}
	
	public Page<Course> findAll(int pageNumber, int pageSize, String direction, String... CourseName) {
		return courseService.findAll(pageNumber, pageSize, direction, CourseName);
	}

	
	public <C extends Course> Optional<C> findOne(Example<C> example) {
		
		return Optional.empty();
	}

	
	public <C extends Course> Page<C> findAll(Example<C> example, Pageable pageable) {
		
		return null;
	}

	
	public <C extends Course> long count(Example<C> example) {
		
		return 0;
	}

	
	public <C extends Course> boolean exists(Example<C> example) {
		
		return false;
	}

	
	public <C extends Course, R> R findBy(Example<C> example, Function<FetchableFluentQuery<C>, R> queryFunction) {
		
		return null;
	}

	
	public void flush() {
		
		
	}

	
	public <S extends Course> S saveAndFlush(S entity) {
		
		return null;
	}

	
	public <S extends Course> List<S> saveAllAndFlush(Iterable<S> entities) {
		
		return null;
	}

	
	public void deleteAllInBatch(Iterable<Course> entities) {
		
		
	}

	
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		
		
	}

	
	public void deleteAllInBatch() {
		
		
	}

	
	public Course getOne(Integer id) {
		
		return null;
	}

	
	public Course getById(Integer id) {
		
		return null;
	}

	
	public Course getReferenceById(Integer id) {
		
		return null;
	}

	
	public <S extends Course> List<S> findAll(Example<S> example) {
		
		return null;
	}

	
	public <S extends Course> List<S> findAll(Example<S> example, Sort sort) {
		
		return null;
	}

}
