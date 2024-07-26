package com.spring.relationalDatabase.bi_manyToMany;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="student_id")
	private int studentId;
	
	private String name;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "student_course"
//        joinColumns = @JoinColumn(name = "student_id"),
//        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
	private Set<Course> courses = new HashSet<>();

}
