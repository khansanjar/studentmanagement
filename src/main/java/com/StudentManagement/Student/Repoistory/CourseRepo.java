package com.StudentManagement.Student.Repoistory;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import com.StudentManagement.Student.Model.Courses; 

public interface CourseRepo extends JpaRepository<Courses, Integer> {
    
	boolean existsBycoursecodeIgnoreCase(String coursecode);

	boolean existsBycoursecodeIgnoreCaseAndIdNot(String coursecode,int id);

	Page<Courses>	findByActiveTrue(PageRequest pageRequest);
	
	List<Courses>	findByActiveTrue(Sort sort);
}