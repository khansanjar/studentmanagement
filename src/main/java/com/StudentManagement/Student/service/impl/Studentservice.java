package com.StudentManagement.Student.service.impl;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;

import com.StudentManagement.Student.DTO.StudentDTO;

public interface Studentservice {

	boolean existsByEmailIgnoreCase(String email);

	StudentDTO createstudent(StudentDTO studentDTO);

	Page<StudentDTO> getStudent(int page, int size);

	StudentDTO getStudentById(int id);

	boolean existsByEmailIgnoreCaseAndIdNot(String email, int id);

	StudentDTO updateStudnet(int id, StudentDTO studentDTO);

	List<StudentDTO> getAllStudent();
	

	

}
