package com.api.api_testing.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "student",
    uniqueConstraints = @UniqueConstraint(columnNames = {"standard", "rollNo"})
)
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String name;
	private int standard;
	private int rollNo;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="Student_course",
	joinColumns= {
			@JoinColumn(name="student_id", referencedColumnName="id")
	},
	inverseJoinColumns= {
			@JoinColumn(name="course_id", referencedColumnName="id")
	})
	private Set<Course> courses;

	public Student() {
		super();
	}
	
	

	public Student(String name, int standard, int rollNo) {
		super();
		this.name = name;
		this.standard = standard;
		this.rollNo = rollNo;
	}



	public Student(String name, int standard, int rollNo, Set<Course> courses) {
		super();
		this.name = name;
		this.standard = standard;
		this.rollNo = rollNo;
		this.courses = courses;
	}
	
	
	public Student(long id, String name, int standard, int rollNo) {
		super();
		this.id = id;
		this.name = name;
		this.standard = standard;
		this.rollNo = rollNo;
	}



	public Student(long id, String name, int standard, int rollNo, Set<Course> courses) {
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



	public Set<Course> getCourses() {
		return courses;
	}



	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}



	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", standard=" + standard + ", rollNo=" + rollNo + ", courses="
				+ courses + "]";
	}
	

}
 