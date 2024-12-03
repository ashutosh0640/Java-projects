package com.api.api_testing.service;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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
import com.api.api_testing.entity.Student;
import com.api.api_testing.exception.StudentNotFoundException;
import com.api.api_testing.repository.StudentRepository;

@Service
public class StudentService {
	
	private StudentRepository studentRepo;
	
	public StudentService(StudentRepository studentRepo) {
		this.studentRepo = studentRepo;
	}


	public <S extends Student> List<S> saveAll(Iterable<S> Students) {
		List<S> StudentList = studentRepo.saveAll(Students);
		return StudentList;
	}

	
	public List<StudentDTO> findAll() {
		return studentRepo.findAll().stream()
				.map(student -> {
					List<CourseDTO> courseDtoList = student.getCourses().stream()
							.map(course -> new CourseDTO(course.getId(), course.getCourseName(), course.getFee(), course.getDuration())).collect(Collectors.toList());
					return new StudentDTO(student.getId(), student.getName(), student.getRollNo(), student.getStandard(), courseDtoList);
				}).collect(Collectors.toList());
	}


	
	public List<Student> findAllById(Iterable<Long> ids) {
		return studentRepo.findAllById(ids);
	}

	
	public <S extends Student> S save(S Student) {
		return studentRepo.save(Student);
	}

	
	public Optional<Student> findById(Long id) {

		return studentRepo.findById(id);
	}

	
	public boolean existsById(Long id) {
		return studentRepo.existsById(id);
	}

	
	public long count() {
		return studentRepo.count();
	}

	
	public void deleteById(Long id) {
		studentRepo.deleteById(id);
		
	}

	
	public void delete(Student Student) {
		studentRepo.delete(Student);
	}

	
	public void deleteAllById(Iterable<? extends Long> ids) {
		studentRepo.deleteAllById(ids);
	}

	
	public void deleteAll(Iterable<? extends Student> Students) {
		studentRepo.deleteAll(Students);
		
	}

	
	public void deleteAll() {
		studentRepo.deleteAll();
	}

	
	public List<Student> findAll(String direction, String... StudentName) {
		Direction dirc = Direction.ASC;
		if(!"ASC".equalsIgnoreCase(direction)) {
			dirc = Direction.DESC;
		}
		Sort sort = Sort.by(dirc, StudentName);
		return studentRepo.findAll(sort);
	}

	
	public Page<Student> findAll(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		return studentRepo.findAll(pageable);
	}
	
	public Page<Student> findAll(int pageNumber, int pageSize, String direction, String... StudentName) {
		Direction dirc = Direction.ASC;
		if(!"ASC".equalsIgnoreCase(direction)) {
			dirc = Direction.DESC;
		}
		Sort sort = Sort.by(dirc, StudentName);
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		return studentRepo.findAll(pageable);
	}

	
	public <S extends Student> Optional<S> findOne(Example<S> example) {
		
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
	
	/*
	 * Return list of student in csv format
	 * @param writer PrintWriter object 
	 * @param students List of studentDTO 
	 * */
	public void writeStudentsToCsv(PrintWriter writer, List<StudentDTO> students) {
	    try {
	        
	        writer.println("ID,Name,Standard,RollNo");
	        
	        
	        for (StudentDTO student : students) {
	            writer.println(String.format("\"%s\",\"%s\",\"%s\",\"%s\"",
	                    student.getId(),
	                    student.getName().replace("\"", "\"\""), // Handle quotes in data
	                    student.getStandard(),
	                    student.getRollNo()));
	        }
	    } catch (Exception e) {
	        throw new RuntimeException("Error while writing CSV data", e);
	    }
	}
	


	
	public void writeStudentsToPdf(OutputStream outputStream, List<StudentDTO> students) {
	    try {
	        Document document = new Document();
	        PdfWriter.getInstance(document, outputStream);
	        document.open();

	        // Add title
	        document.add(new Paragraph("Student List"));

	        // Create a table with headers
	        PdfPTable table = new PdfPTable(4); // 4 columns for ID, Name, Standard, RollNo
	        table.addCell("ID");
	        table.addCell("Name");
	        table.addCell("Standard");
	        table.addCell("RollNo");

	        // Add student data
	        for (StudentDTO student : students) {
	        	
	            table.addCell(String.valueOf(student.getId()));
	            table.addCell(student.getName());
	            table.addCell(String.valueOf(student.getStandard()));
	            table.addCell(String.valueOf(student.getRollNo()));
	        }

	        document.add(table);
	        document.close();
	    } catch (Exception e) {
	        throw new RuntimeException("Error while generating PDF", e);
	    }
	}


	


	public void writeStudentsToExcel(OutputStream outputStream, List<StudentDTO> students) {
	    try (Workbook workbook = new XSSFWorkbook()) {
	        Sheet sheet = workbook.createSheet("Students");

	        // Create header row
	        Row headerRow = sheet.createRow(0);
	        headerRow.createCell(0).setCellValue("ID");
	        headerRow.createCell(1).setCellValue("Name");
	        headerRow.createCell(2).setCellValue("Standard");
	        headerRow.createCell(3).setCellValue("RollNo");

	        // Populate rows with student data
	        int rowNum = 1;
	        for (StudentDTO student : students) {
	            Row row = sheet.createRow(rowNum++);
	            row.createCell(0).setCellValue(student.getId());
	            row.createCell(1).setCellValue(student.getName());
	            row.createCell(2).setCellValue(student.getStandard());
	            row.createCell(3).setCellValue(student.getRollNo());
	        }

	        workbook.write(outputStream);
	    } catch (Exception e) {
	        throw new RuntimeException("Error while generating Excel file", e);
	    }
	}
	
	public void writeStudentsToTxt(PrintWriter writer, List<StudentDTO> students) {
	    try {
	        writer.println("ID\tName\tStandard\tRollNo");
	        for (StudentDTO student : students) {
	            writer.printf("%s\t%s\t%s\t%s%n",
	                    student.getId(),
	                    student.getName(),
	                    student.getStandard(),
	                    student.getRollNo());
	        }
	    } catch (Exception e) {
	        throw new RuntimeException("Error while generating TXT file", e);
	    }
	}



}
