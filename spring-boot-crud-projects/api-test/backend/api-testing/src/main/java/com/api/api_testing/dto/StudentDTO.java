package com.api.api_testing.dto;

import java.util.List;
import java.util.List;

import com.api.api_testing.entity.Course;

public class StudentDTO {
	
	private long id;
	private String name;
	private int standard;
	private int rollNo;
	private List<CourseDTO> courses;
	
	public StudentDTO() {
		super();
	}
	
	
	
	
	public StudentDTO(long id, String name, int standard, int rollNo) {
		super();
		this.id = id;
		this.name = name;
		this.standard = standard;
		this.rollNo = rollNo;
	}




	public StudentDTO(long id, String name, int standard, int rollNo, List<CourseDTO> courses) {
		super();
		this.id = id;
		this.name = name;
		this.standard = standard;
		this.rollNo = rollNo;
		this.courses = courses;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getStandard() {
		return standard;
	}


	public void setStandard(int standard) {
		this.standard = standard;
	}


	public int getRollNo() {
		return rollNo;
	}


	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}


	public List<CourseDTO> getCourses() {
		return courses;
	}


	public void setCourses(List<CourseDTO> courses) {
		this.courses = courses;
	}


	@Override
	public String toString() {
		return "StudentDAO [id=" + id + ", name=" + name + ", standard=" + standard + ", rollNo=" + rollNo
				+ ", courses=" + courses + "]";
	}
	

}
