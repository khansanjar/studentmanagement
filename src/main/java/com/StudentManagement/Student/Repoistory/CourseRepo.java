package com.StudentManagement.Student.Repoistory;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import com.StudentManagement.Student.Model.Courses; // Apne Courses model ka sahi package import karein

public interface CourseRepo extends JpaRepository<Courses, Integer> {
    
	boolean existsBycoursecodeIgnoreCase(String coursecode);
	
	Page<Courses>	findByActiveTrue(PageRequest pageRequest);
}