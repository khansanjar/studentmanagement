package com.StudentManagement.Student.Repoistory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.StudentManagement.Student.Model.Users;

public interface UserRepo extends JpaRepository<Users, Integer> {
	
	boolean existsByUsername(String username);	
	Optional<Users> findByUsername(String username);}
