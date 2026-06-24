package com.StudentManagement.Student.DTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentSummery {

	private int studentId;
	private String studentname;
	private String email;
	private int coursecount;
	private BigDecimal totalfee;
	
	private List<CourseDTO> courselist=new ArrayList<>();
	
	
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getCoursecount() {
		return coursecount;
	}
	public void setCoursecount(int coursecount) {
		this.coursecount = coursecount;
	}
	public BigDecimal getTotalfee() {
		return totalfee;
	}
	public void setTotalfee(BigDecimal totalfee) {
		this.totalfee = totalfee;
	}
	public List<CourseDTO> getCourselist() {
		return courselist;
	}
	public void setCourselist(List<CourseDTO> courselist) {
		this.courselist = courselist;
	}
	
	
	
}
