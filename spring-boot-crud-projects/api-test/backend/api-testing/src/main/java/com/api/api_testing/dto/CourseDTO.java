package com.api.api_testing.dto;

import java.util.List;
import java.util.Set;

public class CourseDTO {

	private int id;
	private String courseName;
	private double fee;
	private int duration;
	private List<StudentDTO> students;
	
	public CourseDTO() {
		super();
	}
	
	
	
	public CourseDTO(int id, String courseName, double fee, int duration) {
		super();
		this.id = id;
		this.courseName = courseName;
		this.fee = fee;
		this.duration = duration;
	}



	public CourseDTO(int id, String courseName, double fee, int duration, List<StudentDTO> students) {
		super();
		this.id = id;
		this.courseName = courseName;
		this.fee = fee;
		this.duration = duration;
		this.students = students;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public List<StudentDTO> getStudents() {
		return students;
	}
	public void setStudents(List<StudentDTO> students) {
		this.students = students;
	}
	@Override
	public String toString() {
		return "CourseDAO [id=" + id + ", courseName=" + courseName + ", fee=" + fee + ", duration=" + duration
				+ ", students=" + students + "]";
	}
	

}
