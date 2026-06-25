package com.StudentManagement.Student.Repoistory;


import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.StudentManagement.Student.Model.Enrollment;

public interface EnrollmentRepo  extends JpaRepository<Enrollment, Integer>{

	
	boolean existsByStudentIdAndCourseId(int studentid,int courseid);
	
	
	@Query("""
				select count( distinct e.student.id) from Enrollment e
				where e.enrollmenttime between :startDate and :endDate
				
			""")
	long 	countDistinctStudentByEnrollDateBetween(@Param("startDate") LocalDateTime startDate,
			@Param ("endDate") LocalDateTime endDate);
}
