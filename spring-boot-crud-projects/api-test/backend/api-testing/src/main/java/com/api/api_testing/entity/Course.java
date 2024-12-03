package com.api.api_testing.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Course {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="course_name", unique=true, nullable=false)
	private String courseName;
	private double fee;
	private int duration;
	
	@ManyToMany(mappedBy="courses")
	private Set<Student> students;

	public Course() {
		super();
	}

	public Course(String courseName, double fee, int duration) {
		super();
		this.courseName = courseName;
		this.fee = fee;
		this.duration = duration;
	}

	public Course(int id, String courseName, double fee, int duration, Set<Student> students) {
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

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", courseName=" + courseName + ", fee=" + fee + ", duration=" + duration
				+ ", students=" + students + "]";
	}

}
