package com.StudentManagement.Student.Repoistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.StudentManagement.Student.Model.StudentClass;


public interface StudentRepo extends JpaRepository<StudentClass, Integer>{
	

	boolean existsByEmailIgnoreCase(String email);
	Page<StudentClass>findByActiveTrue(Pageable pageable);

}
