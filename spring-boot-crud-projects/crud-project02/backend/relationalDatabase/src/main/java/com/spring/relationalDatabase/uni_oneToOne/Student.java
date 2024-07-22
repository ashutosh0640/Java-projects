package com.spring.relationalDatabase.uni_oneToOne;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int studentId;
	
	
	@Column(name="student_name", nullable=false)
	private String studentName;
	
	@Column(unique=true, nullable=false)
	private String email;
	
	@OneToOne
	@JoinColumn(name="course_id")
	private Course courses;

	public Student() {
		super();
	}
	
	

	public Student(String studentName, String email) {
		super();
		this.studentName = studentName;
		this.email = email;
	}



	public Student(String studentName, String email, Course courses) {
		super();
		this.studentName = studentName;
		this.email = email;
		this.courses = courses;
	}



	public int getStudentId() {
		return studentId;
	}



	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}



	public String getStudentName() {
		return studentName;
	}



	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Course getCourses() {
		return courses;
	}



	public void setCourses(Course courses) {
		this.courses = courses;
	}



	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentName=" + studentName + ", email=" + email + ", courses="
				+ courses + "]";
	}
	

}
