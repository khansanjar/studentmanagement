package com.StudentManagement.Student.service.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.StudentManagement.Student.Controller.CoursesController;
import com.StudentManagement.Student.DTO.CourseDTO;
import com.StudentManagement.Student.Model.Courses;
import com.StudentManagement.Student.Repoistory.CourseRepo;


@Service
@Transactional
public class CoursesServiceimpl implements CoursesServices{

	private static final Logger log = LoggerFactory.getLogger(CoursesServiceimpl.class);

	
	private final CourseRepo courserepo;
	
	private final ModelMapper mapper;
	
	public CoursesServiceimpl(CourseRepo courserepo,ModelMapper mapper) {
		this.courserepo=courserepo;
		this.mapper=mapper;
	}
	
	@Override
	public CourseDTO createCourse(CourseDTO coursedto) {
		log.info("Creatting course with unique Code{}",coursedto.getCoursecode());
		Courses courses =mapper.map(coursedto,Courses.class);
		courserepo.save(courses);
		return mapper.map(courses,CourseDTO.class );
	}

	@Override
	public boolean existsByCode(String code) {
		log.info("Checking code Exixts or not :{}",code);
		return courserepo.existsBycoursecodeIgnoreCase(code);
	}

	@Override
	public Page<CourseDTO> getCourses(int page, int size) {
		log.info("list of course  from  :{} ",page);
		
	PageRequest pageRequest=	PageRequest.of(page, size, Sort.by(Direction.DESC,"id"));
	return  courserepo.findByActiveTrue(pageRequest)
	.map(course->mapper.map(course,CourseDTO.class));
		
	}
	

}
