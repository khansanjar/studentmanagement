package com.StudentManagement.Student.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.StudentManagement.Student.DTO.CourseDTO;
import com.StudentManagement.Student.DTO.EnrollmentDTO;
import com.StudentManagement.Student.DTO.EnrollmentSummery;
import com.StudentManagement.Student.DTO.StudentDTO;
import com.StudentManagement.Student.Model.Courses;
import com.StudentManagement.Student.Model.Enrollment;
import com.StudentManagement.Student.Model.StudentClass;
import com.StudentManagement.Student.Repoistory.CourseRepo;
import com.StudentManagement.Student.Repoistory.EnrollmentRepo;
import com.StudentManagement.Student.Repoistory.StudentRepo;

@Service
public class enrollmentserviceimpl implements Enrollmentservice{
	private static final Logger log = LoggerFactory.getLogger(enrollmentserviceimpl.class);

	
		private EnrollmentRepo enrollmentrepo;
		private final StudentRepo studentRepo;
		private final CourseRepo courserepo;
		private final ModelMapper mapper;


		public enrollmentserviceimpl(EnrollmentRepo enrollmentrepo,StudentRepo studentRepo,CourseRepo courserepo,ModelMapper mapper)
		{
			this.enrollmentrepo=enrollmentrepo;
			this.studentRepo=studentRepo;
			this.courserepo=courserepo;
			this.mapper=mapper;
		}
	
		@Override
	public void enrollStudentToCourse(EnrollmentDTO enrollmentDTO) {
		log.info("regestring student agnaist courses");
		StudentClass student=studentRepo.findById(enrollmentDTO.getStudentid())
				.orElseThrow(()->new RuntimeException("Student not found"));
		for(int courseid:enrollmentDTO.getCourseid()) {
			Courses course=courserepo.findById(courseid).
					orElseThrow(()->new RuntimeException("Course not found"));
			if(enrollmentrepo.existsByStudentIdAndCourseId(enrollmentDTO.getStudentid(),courseid ))
			{
				continue;
			}
			Enrollment enrollment=new Enrollment();
			enrollment.setStudent(student);
			enrollment.setCourse(course);
			
			enrollmentrepo.save(enrollment);
			
		}
	}

		@Override
		public Page<EnrollmentSummery> getEnrolledStudent(int page, int size) {

				
				log.info("list of enrolled student   from  :{} ", page);

				PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Direction.DESC, "id"));
				return studentRepo.findEnrolledStudents(pageRequest)
						.map(studentclass -> {
							EnrollmentSummery dto=new EnrollmentSummery();
							dto.setStudentId(studentclass.getId());
							dto.setStudentname(studentclass.getFirstName()+" "+studentclass.getLastName());
							dto.setEmail(studentclass.getEmail());
							
							dto.setCoursecount(studentclass.getEnrollments().size());
							BigDecimal totalfee=studentclass.getEnrollments().stream()
									.map(enrollment->enrollment.getCourse().getFee())
									.filter(fee->fee!=null)
									.reduce(BigDecimal.ZERO, BigDecimal::add);
							dto.setTotalfee(totalfee);
							return dto;
						});
		
		}

		@Override
		public EnrollmentSummery findEnrollStudentCourseDetails(int studentid) {
				
			return studentRepo.findEnrollStudentCourseDetails(studentid).
					map(studentclass ->{
						EnrollmentSummery dto=new EnrollmentSummery();
						dto.setStudentId(studentclass.getId());
						dto.setStudentname(studentclass.getFirstName()+" "+studentclass.getLastName());
						dto.setEmail(studentclass.getEmail());
						
						dto.setCoursecount(studentclass.getEnrollments().size());
						BigDecimal totalfee=studentclass.getEnrollments().stream()
								.map(enrollment->enrollment.getCourse().getFee())
								.filter(fee->fee!=null)
								.reduce(BigDecimal.ZERO, BigDecimal::add);
						dto.setTotalfee(totalfee);
						
						List<CourseDTO> coursselist=studentclass.getEnrollments().stream()
								.map(enrollment->enrollment.getCourse())
								.map(course->mapper.map(course,CourseDTO.class))
								.collect(Collectors.toList());
						
						dto.setCourselist(coursselist);
						return dto;
					}).orElseThrow(()->new RuntimeException("studnet not found"));
			
		}			
	}


