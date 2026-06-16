package com.StudentManagement.Student.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.server.csrf.CsrfException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class Exceptionhandler {

		private static final Logger log = LoggerFactory.getLogger(Exceptionhandler.class);
			
		@ExceptionHandler(CsrfException.class)
		public String csrfHandler(CsrfException ex, RedirectAttributes redirect) {
			log.warn("CSRF validation failed: {}", ex.getMessage());
			redirect.addFlashAttribute("message", "Session Expired, login again");
			return "redirect:/login";
		}
		
		@ExceptionHandler(Exception.class)
		@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
		public String genericExceptionHandler(Exception ex) {
			log.error("Internal Server Error Triggered: {}", ex.getMessage());
			return "500"; // returns 500.html template
		}
}