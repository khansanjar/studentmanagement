package com.StudentManagement.Student.DTO;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CourseDTO {
	

	private int id;
	
	@NotBlank(message="Course Name is required")
	@Size(max=150,message="max 150 charcater is allowed")
	private String coursename;
	
	@NotBlank(message="Course code is required")
	private String coursecode;
	
	@NotBlank(message="Course duration is required")
	private String duration;

	
	@NotNull(message="Course fee is required")
	private BigDecimal fee;

	@Size(max=500,message="max 500 charcater is allowed")

	private String descriptation;
	
	private boolean active;


	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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
	
}
