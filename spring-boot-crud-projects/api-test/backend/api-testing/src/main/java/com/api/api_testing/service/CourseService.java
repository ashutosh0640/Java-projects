package com.api.api_testing.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.api.api_testing.dto.CourseDTO;
import com.api.api_testing.dto.StudentDTO;
import com.api.api_testing.entity.Course;
import com.api.api_testing.entity.Student;
import com.api.api_testing.exception.CourseNotFoundException;
import com.api.api_testing.repository.CourseRepository;

@Service
public class CourseService {
	
	
	private CourseRepository courseRepo;
	
	public CourseService(CourseRepository courseRepo) {
		this.courseRepo = courseRepo;
	}


	public List<CourseDTO> saveAll(Iterable<Course> courses) {
		List<Course> courseList = courseRepo.saveAll(courses);
		List<CourseDTO> DTO = new ArrayList<>();
		for(Course course : courseList) {
			CourseDTO cDTO = changeCourseIntoCourseDTO(course);
			DTO.add(cDTO);
		}
		return DTO;
	}

	
	public List<CourseDTO> findAll() {
	    return courseRepo.findAll().stream()
	        .map(course -> {
	        	List<StudentDTO> studentDtoList = course.getStudents().stream()
	        			.map(student -> new StudentDTO(student.getId(), student.getName(), student.getRollNo(), student.getStandard())).collect(Collectors.toList());
	        	return new CourseDTO(course.getId(), course.getCourseName(), course.getFee(), course.getDuration(), studentDtoList);
	        }).collect(Collectors.toList());
	}


	
	public List<CourseDTO> findAllById(Iterable<Integer> ids) {
		List<Course> courseList =  courseRepo.findAllById(ids);
		List<CourseDTO> DTO = new ArrayList<>();
		for(Course course : courseList) {
			CourseDTO cDTO = changeCourseIntoCourseDTO(course);
			DTO.add(cDTO);
		}
		return DTO;
	}

	
	public CourseDTO save(Course course) {
		Course savedCourse =  courseRepo.save(course);
		CourseDTO cDTO = changeCourseIntoCourseDTO(savedCourse);
		return cDTO;
	}

	
	public CourseDTO findById(Integer id) {
		Course course = courseRepo.findById(id)
				.orElseThrow(()->new CourseNotFoundException("Course by id: "+ id+" not found."));
		
		CourseDTO cDTO = changeCourseIntoCourseDTO(course);
		return cDTO;
	}

	
	public boolean existsById(Integer id) {
		return courseRepo.existsById(id);
	}

	
	public long count() {
		return courseRepo.count();
	}

	
	public void deleteById(Integer id) {
		courseRepo.deleteById(id);
		
	}

	
	public void delete(Course course) {
		courseRepo.delete(course);
	}

	
	public void deleteAllById(Iterable<? extends Integer> ids) {
		courseRepo.deleteAllById(ids);
	}

	
	public void deleteAll(Iterable<? extends Course> courses) {
		courseRepo.deleteAll(courses);
		
	}

	
	public void deleteAll() {
		courseRepo.deleteAll();
	}

	
	public List<Course> findAll(String direction, String... courseName) {
		Direction dirc = Direction.ASC;
		if(!"ASC".equalsIgnoreCase(direction)) {
			dirc = Direction.DESC;
		}
		Sort sort = Sort.by(dirc, courseName);
		return courseRepo.findAll(sort);
	}

	
	public Page<Course> findAll(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		return courseRepo.findAll(pageable);
	}
	
	public Page<Course> findAll(int pageNumber, int pageSize, String direction, String... courseName) {
		Direction dirc = Direction.ASC;
		if(!"ASC".equalsIgnoreCase(direction)) {
			dirc = Direction.DESC;
		}
		Sort sort = Sort.by(dirc, courseName);
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		return courseRepo.findAll(pageable);
	}

	
	public <S extends Course> Optional<S> findOne(Example<S> example) {
		
		return Optional.empty();
	}

	
	public <S extends Course> Page<S> findAll(Example<S> example, Pageable pageable) {
		
		return null;
	}

	
	public <S extends Course> long count(Example<S> example) {
		
		return 0;
	}

	
	public <S extends Course> boolean exists(Example<S> example) {
		
		return false;
	}

	
	public <S extends Course, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		
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
	
	public static void writeStudentsToCsv(PrintWriter writer, List<Student> students) {
		writer.write("ID, Name, Standared, RollNo\n");
		for (Student student : students) {
			writer.write(student.getId() + ", " + student.getName() + ", " + student.getStandard() + ", "
					+ student.getRollNo() + "\n");
		}
	}
	
	
	public CourseDTO changeCourseIntoCourseDTO(Course course) {
		
		List<StudentDTO> studentList = new ArrayList<StudentDTO>();
		
		for(Student student : course.getStudents()) {
			StudentDTO sDTO = new StudentDTO();
			sDTO.setId(student.getId());
			sDTO.setName(student.getName());
			sDTO.setStandard(student.getStandard());
			sDTO.setRollNo(student.getRollNo());
			studentList.add(sDTO);
		}
		
		CourseDTO dto = new CourseDTO();
		dto.setId(course.getId());
		dto.setCourseName(course.getCourseName());
		dto.setDuration(course.getDuration());
		dto.setFee(course.getFee());
		dto.setStudents(studentList);
		
		return dto;
		
	}
	
	public Course changeCourseDTOIntoCourse(CourseDTO courseDTO) {
		return null;
		
	}

}
