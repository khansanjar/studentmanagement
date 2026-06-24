package com.StudentManagement.Student.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.StudentManagement.Student.DTO.CourseDTO;
import com.StudentManagement.Student.DTO.StudentDTO;
import com.StudentManagement.Student.Model.Courses;
import com.StudentManagement.Student.Model.StudentClass;
import com.StudentManagement.Student.Repoistory.StudentRepo;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional
public class Studentserviceimpl implements Studentservice {

	private static final Logger log = LoggerFactory.getLogger(Studentserviceimpl.class);

	private final StudentRepo studentRepo;
	private final ModelMapper mapper;

	public Studentserviceimpl(StudentRepo studentRepo, ModelMapper mapper) {
		this.studentRepo = studentRepo;
		this.mapper = mapper;
	}

	@Override
	public boolean existsByEmailIgnoreCase(String email) {

		log.info("email from student");
		return studentRepo.existsByEmailIgnoreCase(email);
	}

	@Override
	public StudentDTO createstudent(StudentDTO studentDTO) {

		log.info("saving studnet data ");

		StudentClass student = mapper.map(studentDTO, StudentClass.class);

		StudentClass saved = studentRepo.save(student);
		return mapper.map(saved, StudentDTO.class);
	}

	@Override
	public Page<StudentDTO> getStudent(int page, int size) {

		log.info("list of student  from  :{} ", page);

		PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Direction.DESC, "id"));
		return studentRepo.findByActiveTrue(pageRequest)
				.map(studentclass -> mapper.map(studentclass, StudentDTO.class));
	}

	@Override
	@Transactional(readOnly=true)
	public StudentDTO getStudentById(int id) {
		
		StudentClass student=studentRepo.findById(id).orElseThrow(()->new RuntimeException ("N student found"));
		
		return mapper.map(student,StudentDTO.class);
	}

	@Override
	public boolean existsByEmailIgnoreCaseAndIdNot(String email, int id) {
		
		log.info("email form student update");
		
		return studentRepo.existsByEmailIgnoreCaseAndIdNot(email, id);
	}

	@Override
	public StudentDTO updateStudnet(int id, StudentDTO studentDTO) {
		StudentClass student= 	studentRepo.findById(id).orElseThrow(()-> new RuntimeException 	("Student Id Not Found"));
		
		mapper.map(studentDTO,student);
		
			
	StudentClass	update=studentRepo.saveAndFlush(student);
		
			return  mapper.map(update,StudentDTO.class);	
			
	
	
	
	}
	
	@Override
	public List<StudentDTO> getAllStudent() {
	    return studentRepo.findByActiveTrue().stream()
	            .map(student -> mapper.map(student, StudentDTO.class))
	            .collect(Collectors.toList());
	}

	

}


























