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
import com.StudentManagement.Student.DTO.StudentDTO;
import com.StudentManagement.Student.service.impl.Studentservice;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/student")
public class StudentController {
		
	private static final Logger log = LoggerFactory.getLogger(StudentController.class);
	
private final Studentservice studentservice;
    
StudentController(Studentservice studentservice){
		 this.studentservice = studentservice;
	}

//Method
@GetMapping("/new")
public String shoecreateStudent(Model model)
{
			log.info("Get/new -showing creadt student");
		model.addAttribute("studentDTO",new StudentDTO());
	return "add-student";
}

@GetMapping("/list")
public String studentlist(@RequestParam(defaultValue = "0")int page,@RequestParam(defaultValue = "5")int size
		,Model model,@RequestParam(value="message",required=false)String message)
{
log.info("Get /list  -showing list of student");

Page<StudentDTO> studentDTO  =	studentservice.getStudent(page, size);
model.addAttribute("student",studentDTO);
model.addAttribute("message",message);

	return "student";
}







@PostMapping("/save")
public String createstudent(@Valid @ModelAttribute("studentDTO") StudentDTO studentDTO, BindingResult result,
			Model model, RedirectAttributes redirect) {
	log.info("Post/save  -create student request");
	if(result.hasErrors()) {
		return "add-student";
	}
	if(studentservice.existsByEmailIgnoreCase(studentDTO.getEmail())) {
		log.error("Post/save -page return Code must must be unique");
		
		result.rejectValue("email", "error.studentDTO", "email must be Unique");

		return "add-student";
	}
	redirect.addAttribute("message", "Student   Is added Successfuly");
	log.info("Post  /studnet  -create course Successfully");

	
	studentservice.createstudent(studentDTO);

	return "redirect:/student/list";
	
}
@GetMapping("/{id}")
public String getStudentById(@PathVariable int id,Model model) {

	StudentDTO student=studentservice.getStudentById(id);
	model.addAttribute("student", student);
	
	return "view-student";
	
}

@GetMapping("/{id}/edit")
public String editstudent(@PathVariable int id,Model model) {

	StudentDTO student=studentservice.getStudentById(id);
	model.addAttribute("studentDTO", student);
	
	return "edit-student";
	
}
@PostMapping("/{id}/update")
public String updateStudent(@PathVariable int id,
		@Valid @ModelAttribute("courseDTO") StudentDTO studentDTO, BindingResult result,
		Model model, RedirectAttributes redirect) {
	
	log.info("Post/save  -update student request");
	if(result.hasErrors()) {
		return "edit-student";
	}
	if(studentservice.existsByEmailIgnoreCaseAndIdNot(studentDTO.getEmail(), id)) {
		log.error("Post/update -page return Email must must be unique");
		
		result.rejectValue("email", "error.studentDTO", "email must be Unique");

		return "edit-student";
	}
	studentservice.updateStudnet(id,studentDTO);
	redirect.addAttribute("message", "Student   Is updated Successfuly");
	
	return "redirect:/student/list";
	
}
}





