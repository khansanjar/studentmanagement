package com.StudentManagement.Student.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;

@Entity
public class Courses {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)

	private int id;
	
	@Column(nullable=false)
	private String coursename;
	
	@Column(nullable=false,unique=true)
	private String coursecode;
	
	private String duration;
	
	@Column (name="active",nullable=false)
	private boolean active=true;
	
	@Column(precision =12 ,scale=2,nullable=false)
	private BigDecimal fee;
	
	@Column(length=100)
	private String descriptation;
	
	@Column(nullable=false,updatable=false)
	 private LocalDateTime createdAt;
	
	
	@OneToMany(mappedBy="course",cascade=CascadeType.ALL, orphanRemoval=true)
	private Set<Enrollment> enrollments=new HashSet<>();


	@PrePersist
	public void onCreate() {
		createdAt=LocalDateTime.now();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public String getCoursecode() {
		return coursecode;
	}

	public void setCoursecode(String coursecode) {
		this.coursecode = coursecode;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public String getDescriptation() {
		return descriptation;
	}

	public void setDescriptation(String descriptation) {
		this.descriptation = descriptation;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	
	public Set<Enrollment> getEnrollments() {
		return enrollments;
	}
	
	public void setEnrollments(Set<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}
}
