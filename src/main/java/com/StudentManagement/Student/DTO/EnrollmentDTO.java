package com.StudentManagement.Student.DTO;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotNull;

public class EnrollmentDTO {
	
	@NotNull(message="Student is required")
	private int studentid;
	 
	@NotNull(message="select at lest one course")

	private List<Integer> courseid = new ArrayList<>();

	public int getStudentid() {
		return studentid;
	}

	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}

	public List<Integer> getCourseid() {
		return courseid;
	}

	public void setCourseid(List<Integer> courseid) {
		this.courseid = courseid;
	}	
	

}
