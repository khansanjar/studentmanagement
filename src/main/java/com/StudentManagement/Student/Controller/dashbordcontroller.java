package com.StudentManagement.Student.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class dashbordcontroller {
	
	@GetMapping("/dashboard")
	public String dashbord() {
		return "dashboard";
	}
}
