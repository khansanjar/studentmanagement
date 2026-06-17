package com.StudentManagement.Student.service.impl;

import org.springframework.data.domain.Page;

import com.StudentManagement.Student.DTO.CourseDTO;

public interface CoursesServices {

	CourseDTO createCourse (CourseDTO coursedto);
	boolean existsByCode(String code);
boolean existsBycoursecodeIgnoreCaseAndIdNot(String code,int id);	
	Page<CourseDTO> getCourses(int page,int size);
	
	CourseDTO getCourseById(int id);
	
	CourseDTO updateCourse (int id,CourseDTO coursedto);

}
