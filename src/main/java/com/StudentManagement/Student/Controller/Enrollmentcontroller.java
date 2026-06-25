package com.StudentManagement.Student.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.StudentManagement.Student.DTO.CourseDTO;
import com.StudentManagement.Student.DTO.EnrollmentDTO;
import com.StudentManagement.Student.DTO.EnrollmentSummery;
import com.StudentManagement.Student.DTO.StudentDTO;
import com.StudentManagement.Student.service.impl.CoursesServices;
import com.StudentManagement.Student.service.impl.Enrollmentservice;
import com.StudentManagement.Student.service.impl.Studentservice;

import jakarta.validation.Valid;

@Controller


@RequestMapping("/enrollments")
public class Enrollmentcontroller {

	private static final Logger log = LoggerFactory.getLogger(Enrollmentcontroller.class);

	private final CoursesServices coursesservice;
	private final Studentservice studentservice;
	private final Enrollmentservice enrollmentservice;
	
	public Enrollmentcontroller(CoursesServices coursesservice,Studentservice studentservice,Enrollmentservice enrollmentservice)
	{
		this.coursesservice=coursesservice;
		this.studentservice=studentservice;
		this.enrollmentservice=enrollmentservice;
	}
	
	
	@GetMapping("/showenroll")
	public String showEnroll(Model model) {
		
		log.info("Get /enrollments /show enroll  -showing create course page");
		
		model.addAttribute("enrollmentDTO", new EnrollmentDTO());	
		model.addAttribute("courses", coursesservice.getAllCourses());
		model.addAttribute("studentlist", studentservice.getAllStudent());
		return "enroll-course";
	}
	
	
	
	
	@GetMapping("/enrollmentlist")
	public String enrollmentlist(@RequestParam(defaultValue = "0")int page,@RequestParam(defaultValue = "5")int size
			,Model model)
	{
	log.info("Get /enrollmentlist  -showing enroll list of  student");

	Page<EnrollmentSummery> student  =	enrollmentservice.getEnrolledStudent(page, size);
	model.addAttribute("student",student);
//	model.addAttribute("message",message);

		return "enrolled-student";
	}
	
	
	
	
	
	@PostMapping("/enrollCourse")
	public String enrollCourse(@Valid @ModelAttribute("enrollmentDTO") EnrollmentDTO enrollmentDTO, BindingResult result,
			Model model, RedirectAttributes redirect) {
        
		log.info("Post  /enrollcourse  -enrollment request recived");
		if(result.hasErrors()) {
		
			model.addAttribute("courses", coursesservice.getAllCourses());
			model.addAttribute("studentlist", studentservice.getAllStudent());
			return "enroll-course";
		}
		
		
		enrollmentservice.enrollStudentToCourse(enrollmentDTO);
		
		
		redirect.addFlashAttribute("message", "enrollment  Is  Successfull");
		log.info("Post /enrollments /course  -enrollment  Is  Successfull");
        

		return "redirect:/enrollments/enrollmentlist";
	
	}	
	
	@GetMapping("/getStudentEnrollmentDetails/{id}")
	public String getStudentEnrollmentDetails(@PathVariable int id ,Model model,
			@RequestParam(defaultValue="enrollments")String source) {
		
		EnrollmentSummery summerydto=enrollmentservice.findEnrollStudentCourseDetails(id);
		model.addAttribute("summerydto",summerydto);
		model.addAttribute("source",source);
		return "enrollment-details";
	}
	
	
	
	
	
	
	
	
	
}
