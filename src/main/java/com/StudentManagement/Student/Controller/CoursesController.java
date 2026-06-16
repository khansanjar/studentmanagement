package com.StudentManagement.Student.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.StudentManagement.Student.DTO.CourseDTO;
import com.StudentManagement.Student.exception.Exceptionhandler;
import com.StudentManagement.Student.service.impl.CoursesServices;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/course")
public class CoursesController {
	
	private static final Logger log = LoggerFactory.getLogger(CoursesController.class);

	
	private final CoursesServices coursesservicrs;
    
	CoursesController(CoursesServices coursesservicrs){
		 this.coursesservicrs = coursesservicrs;
	}
	
	@GetMapping("/new")
	public String showCreateCourse(Model model) {
		
		log.info("Get /course /new  -showing create course page");
		
		model.addAttribute("courseDTO", new CourseDTO());
		return "add-course";
	}

	@GetMapping("/list")
	public String listCourse(@RequestParam(defaultValue = "0")int page,@RequestParam(defaultValue = "5")int size
			,Model model) {
		log.info("Get /course /list  -showing create course list");
		
		Page<CourseDTO> cources  =	coursesservicrs.getCourses(page, size);
		model.addAttribute("cources",cources);
		return "course";
	}

	@PostMapping
	public String createcourse(@Valid @ModelAttribute("courseDTO") CourseDTO courseDTO, BindingResult result,
			Model model, RedirectAttributes redirect) {
        
		log.info("Post  /course  -create course request recived");

		
		// 1. Agar JSR-303 validations (Not Null, Size etc.) fail hon
		if(result.hasErrors()) {
			log.error("Post/course -page return due to error occours");
			return "add-course";
		}

		// 2. Unique Course Code check karne ka sahi tareeqa (rejectValue use karein)
		if(coursesservicrs.existsByCode(courseDTO.getCoursecode())) {
			log.error("Post/course -page return Code must must be unique");
			
			result.rejectValue("coursecode", "error.courseDTO", "Code must be Unique");
			return "add-course";
		}

		coursesservicrs.createCourse(courseDTO);
        
		// 3. Success message redirect ke sath carry karne ke liye flash attribute behtar hai
		redirect.addFlashAttribute("message", "course created");
		log.info("Post  /course  -create course Successfully");
        
		// 4. Form submission ke baad hamesha "redirect:" prefix lagana zaroori hai
		return "redirect:/course/list";
	}
}