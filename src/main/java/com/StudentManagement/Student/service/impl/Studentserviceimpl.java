package com.StudentManagement.Student.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.StudentManagement.Student.DTO.StudentDTO;
import com.StudentManagement.Student.Model.StudentClass;
import com.StudentManagement.Student.Repoistory.StudentRepo;

import jakarta.transaction.Transactional;
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

}
