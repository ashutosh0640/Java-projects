package com.api.api_testing.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.apache.poi.ss.usermodel.Workbook;
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

import com.api.api_testing.dto.StudentDTO;
import com.api.api_testing.entity.Student;
import com.api.api_testing.exception.StudentNotFoundException;
import com.api.api_testing.service.CourseService;
import com.api.api_testing.service.StudentService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	private final StudentService studentService;
	private final CourseService courseService;
	
	public StudentController(StudentService studentService, CourseService courseService) {
		this.studentService = studentService;
		this.courseService = courseService;
	}


	@PostMapping("/saveAll")
	public <S extends Student> List<S> saveAll(@RequestBody Iterable<S> Students) {
		List<S> StudentList = studentService.saveAll(Students);
		return StudentList;
	}

	@GetMapping("/findAll")
	public List<StudentDTO> findAll() {
		return studentService.findAll();
	}

	@GetMapping("/findAllById")
	public List<Student> findAllById(@RequestBody Iterable<Long> ids) {
		return studentService.findAllById(ids);
	}

	@PostMapping("/save")
	public <S extends Student> S save(@RequestBody S Student) {
		return studentService.save(Student);
	}
	

	@GetMapping("/findById")
	public Student findById(@RequestParam Long id) {
		return studentService.findById(id)
		.orElseThrow(()->new StudentNotFoundException("Student by id: "+ id+" not found."));
		
	}

	@GetMapping("/exists")
	public boolean existsById(@RequestParam Long id) {
		return studentService.existsById(id);
	}

	@GetMapping("/count")
	public long count() {
		return studentService.count();
	}

	@DeleteMapping("/deleteById")
	public void deleteById(@RequestParam Long id) {
		studentService.deleteById(id);
		
	}

	@DeleteMapping("/delete")
	public void delete(@RequestBody Student Student) {
		studentService.delete(Student);
	}

	@DeleteMapping("/deleteAllById")
	public void deleteAllById(@RequestBody Iterable<? extends Long> ids) {
		studentService.deleteAllById(ids);
	}

	@DeleteMapping("/deleteAllByStudents")
	public void deleteAll(@RequestBody Iterable<? extends Student> Students) {
		studentService.deleteAll(Students);
	}

	@DeleteMapping("/deleteAll")
	public void deleteAll() {
		studentService.deleteAll();
	}

	@GetMapping("/findAll/sort")
	public List<Student> findAll(@RequestBody String direction, String... StudentName) {
		return studentService.findAll(direction, StudentName);
	}

	@GetMapping("/findAll/page")
	public Page<Student> findAll(@RequestParam int pNum, @RequestParam int pSize) {
		return studentService.findAll(pNum, pSize);
	}
	
	@GetMapping("/findAll/page/sort")
	public Page<Student> findAll(@RequestParam int pNum, @RequestParam int pSize, @RequestParam String dirc, @RequestBody String... StudentName) {
		return studentService.findAll(pSize, pSize, dirc, StudentName);
	}

	
	public <S extends Student> Optional<S> findOne(@RequestBody S example) {
		return Optional.empty();
	}

	
	public <S extends Student> Page<S> findAll(Example<S> example, Pageable pageable) {
		
		return null;
	}

	
	public <S extends Student> long count(Example<S> example) {
		
		return 0;
	}

	
	public <S extends Student> boolean exists(Example<S> example) {
		
		return false;
	}

	
	public <S extends Student, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		
		return null;
	}

	
	public void flush() {
		
		
	}

	
	public <S extends Student> S saveAndFlush(S entity) {
		
		return null;
	}

	
	public <S extends Student> List<S> saveAllAndFlush(Iterable<S> entities) {
		
		return null;
	}

	
	public void deleteAllInBatch(Iterable<Student> entities) {
		
		
	}

	
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		
		
	}

	
	public void deleteAllInBatch() {
		
		
	}

	
	public Student getOne(Long id) {
		
		return null;
	}

	
	public Student getById(Long id) {
		
		return null;
	}

	
	public Student getReferenceById(Long id) {
		
		return null;
	}

	
	public <S extends Student> List<S> findAll(Example<S> example) {
		
		return null;
	}

	
	public <S extends Student> List<S> findAll(Example<S> example, Sort sort) {
		
		return null;
	}
	
    @GetMapping("/export-csv")
    public void exportToCsv(HttpServletResponse response) {
        try {
            // Set response headers
            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=\"students.csv\"");
            
            List<StudentDTO> students = studentService.findAll();

            PrintWriter writer = response.getWriter();
            studentService.writeStudentsToCsv(writer, students);
        } catch (IOException e) {
            throw new RuntimeException("Error generating CSV file", e);
        }
    }
	
    @GetMapping("/export-pdf")
    public void exportToPdf(HttpServletResponse response) {
        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"students.pdf\"");

            List<StudentDTO> students = studentService.findAll();
            studentService.writeStudentsToPdf(response.getOutputStream(), students);
        } catch (IOException e) {
            throw new RuntimeException("Error generating PDF file", e);
        }
    }

    @GetMapping("/export-excel")
    public void exportToExcel(HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=\"students.xlsx\"");

            List<StudentDTO> students = studentService.findAll();
            studentService.writeStudentsToExcel(response.getOutputStream(), students);
        } catch (IOException e) {
            throw new RuntimeException("Error generating Excel file", e);
        }
    }
    
    @GetMapping("/export-txt")
    public void exportToTxt(HttpServletResponse response) {
        try {
            response.setContentType("text/plain");
            response.setHeader("Content-Disposition", "attachment; filename=\"students.txt\"");

            List<StudentDTO> students = studentService.findAll();
            PrintWriter writer = response.getWriter();
            studentService.writeStudentsToTxt(writer, students);
        } catch (IOException e) {
            throw new RuntimeException("Error generating TXT file", e);
        }
    }
    
}
