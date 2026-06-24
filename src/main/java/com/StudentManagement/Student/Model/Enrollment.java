package com.StudentManagement.Student.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Enrollment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)

	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="student_id",nullable=false)
	private StudentClass student;

	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="course_id",nullable=false)
	private Courses course;
	
	private LocalDateTime enrollmenttime=LocalDateTime.now();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public StudentClass getStudent() {
		return student;
	}

	public void setStudent(StudentClass student) {
		this.student = student;
	}

	public Courses getCourse() {
		return course;
	}

	public void setCourse(Courses course) {
		this.course = course;
	}

	public LocalDateTime getEnrollmenttime() {
		return enrollmenttime;
	}

	public void setEnrollmenttime(LocalDateTime enrollmenttime) {
		this.enrollmenttime = enrollmenttime;
	}
	

	
}
