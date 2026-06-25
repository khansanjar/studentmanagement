package com.StudentManagement.Student.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.StudentManagement.Student.service.impl.CoursesServices;
import com.StudentManagement.Student.service.impl.DashBoardService;
import com.StudentManagement.Student.service.impl.Enrollmentservice;
import com.StudentManagement.Student.service.impl.Studentservice;

@Controller
public class dashbordcontroller {
	
//
//	private final CoursesServices coursesservice;
//	private final Studentservice studentservice;
	private final Enrollmentservice enrollmentservice;
	private final DashBoardService dashboardservice;
	
	dashbordcontroller(Enrollmentservice enrollmentservice,DashBoardService dashboardservice){
		this.enrollmentservice= enrollmentservice;
		this.dashboardservice=dashboardservice;
	}
	
	@GetMapping("/dashboard")
	public String dashbord(Model model) {
		model.addAttribute("student", enrollmentservice.getRecentEnrolledStudent());
		model.addAttribute("dashboardstats",dashboardservice.getDashBoardstats());
		
		return "dashboard";
	}
}
