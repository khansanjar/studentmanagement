package com.StudentManagement.Student.service.impl;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;

import com.StudentManagement.Student.DTO.StudentDTO;
import com.StudentManagement.Student.Model.StudentClass;

public interface Studentservice {
	
	boolean existsByEmailIgnoreCase(String email);
	StudentDTO createstudent(StudentDTO studentDTO);
	Page <StudentDTO> getStudent (int page, int size);


}
 