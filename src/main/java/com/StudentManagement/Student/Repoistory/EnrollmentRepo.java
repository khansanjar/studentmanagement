package com.StudentManagement.Student.Repoistory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.StudentManagement.Student.Model.Enrollment;

public interface EnrollmentRepo  extends JpaRepository<Enrollment, Integer>{

	
	boolean existsByStudentIdAndCourseId(int studentid,int courseid);
}
