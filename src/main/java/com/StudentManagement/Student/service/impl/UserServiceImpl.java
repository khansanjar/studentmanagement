package com.StudentManagement.Student.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.StudentManagement.Student.Model.Users;
import com.StudentManagement.Student.Repoistory.UserRepo;

@Service
public class UserServiceImpl implements UserDetailsService {
		private	UserRepo userrepo;
		
		public UserServiceImpl(UserRepo userrepo) {
			this.userrepo=userrepo;
		}
		
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	 Users user=	userrepo.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Invalid username!"));
	 return User.withUsername(username)
			 .password(user.getPassword())
			 .disabled(!user.isIsastive())
			 .build();
		
	}
	

}
