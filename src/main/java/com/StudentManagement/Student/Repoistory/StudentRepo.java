package com.StudentManagement.Student.Repoistory;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.StudentManagement.Student.Model.StudentClass;


public interface StudentRepo extends JpaRepository<StudentClass, Integer>{
	
	boolean existsByEmailIgnoreCase(String email);
	Page<StudentClass> findByActiveTrue(Pageable pageable);
	boolean existsByEmailIgnoreCaseAndIdNot(String email, int id);
	List<StudentClass> findByActiveTrue();
		
	@EntityGraph(attributePaths= {"enrollments","enrollments.course"})
	@Query(
		    value = """
		            select distinct s from StudentClass s join s.enrollments e
		            """,
		    countQuery = """
		                 select count(distinct s)
		                 from StudentClass s
		                 join s.enrollments e
		                 """
		)
		Page<StudentClass> findEnrolledStudents(Pageable pageable);
	
	@Query("""
			select s from StudentClass s
			join fetch s.enrollments e
			join fetch e.course 
			where s.id= :id
			"""
			)
	
	
	
	Optional	<StudentClass>	findEnrollStudentCourseDetails(@Param("id")int id);

}