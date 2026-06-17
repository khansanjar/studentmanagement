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
			,Model model,@RequestParam(value="message",required=false)String message) {
		log.info("Get /course /list  -showing create course list");
		
		Page<CourseDTO> cources  =	coursesservicrs.getCourses(page, size);
		model.addAttribute("cources",cources);
		model.addAttribute("message",message);

		return "course";
	}

	@PostMapping
	public String createcourse(@Valid @ModelAttribute("courseDTO") CourseDTO courseDTO, BindingResult result,
			Model model, RedirectAttributes redirect) {
        
		log.info("Post  /course  -create course request recived");

		

		if(result.hasErrors()) {
			log.error("Post/course -page return due to error occours");
			return "add-course";
		}

		if(coursesservicrs.existsByCode(courseDTO.getCoursecode())) {
			log.error("Post/course -page return Code must must be unique");
			
			result.rejectValue("coursecode", "error.courseDTO", "Code must be Unique");
			return "add-course";
		}

		coursesservicrs.createCourse(courseDTO);
        

		redirect.addAttribute("message", "Course  Is Created Successfuly");
		log.info("Post  /course  -create course Successfully");
        

		return "redirect:/course/list";
	}
	
	@GetMapping("/{id}")
	public String getCourseById(@PathVariable int id,Model model) {
	
		CourseDTO course=coursesservicrs.getCourseById(id);
		model.addAttribute("course", course);
		
		return "view-course";
		
	}
	
	
	@GetMapping("/{id}/edit")
	public String editcourse(@PathVariable int id,Model model) {
	
		CourseDTO course=coursesservicrs.getCourseById(id);
		model.addAttribute("courseDTO", course);
		
		return "edit-course";
		
	}
	
	@PostMapping("/{id}/update")
	public String updatecourse(@PathVariable int id,
			@Valid @ModelAttribute("courseDTO") CourseDTO courseDTO, BindingResult result,
			Model model, RedirectAttributes redirect) {
		
		
log.info("Post {id} /update  -Update course request recived {} :",id);

		

		if(result.hasErrors()) {
			log.error("Post {id} /update -page return due to error occours");
			return "edit-course";
		}

		if(coursesservicrs.existsBycoursecodeIgnoreCaseAndIdNot(courseDTO.getCoursecode(), id)) {
			log.error("Post {id} /update  -page return Code must must be unique");
			
			result.rejectValue("coursecode", "error.courseDTO", "Code must be Unique");
			return "edit-course";
		}

		coursesservicrs.updateCourse(id, courseDTO);
        

		redirect.addAttribute("message", "Course  Is updated Successfuly");
		log.info("Post {id} /update  -create course Successfully");
        

		return "redirect:/course/list";
	}
}






















