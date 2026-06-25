package com.StudentManagement.Student.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;

import com.StudentManagement.Student.DTO.EnrollmentDTO;
import com.StudentManagement.Student.DTO.EnrollmentSummery;
import com.StudentManagement.Student.DTO.StudentDTO;

public interface Enrollmentservice {

	
	
	void enrollStudentToCourse(EnrollmentDTO enrollmentDTO);
	
	Page<EnrollmentSummery> getEnrolledStudent(int page, int size);
	
	
	
	EnrollmentSummery findEnrollStudentCourseDetails(int studentid);
	
	
	List<EnrollmentSummery> getRecentEnrolledStudent();


	}
