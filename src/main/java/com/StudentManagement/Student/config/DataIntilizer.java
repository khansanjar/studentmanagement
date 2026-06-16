package com.StudentManagement.Student.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.StudentManagement.Student.Model.Users;
import com.StudentManagement.Student.Repoistory.UserRepo;

@Configuration
public class DataIntilizer {


	@Bean
	CommandLineRunner LoadSimpleData(UserRepo userrepo,PasswordEncoder encoder) {
		 
		return args->{
			if(!userrepo.existsByUsername("admin")) {
				
				Users user=new Users();
				user.setUsername("admin");
			user.setPassword(encoder.encode("admin@123"));
			user.setIsastive(true);
			userrepo.save(user);
			}
		};
	}
}
