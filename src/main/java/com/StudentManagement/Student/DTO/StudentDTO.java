package com.StudentManagement.Student.DTO;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class StudentDTO {

	
	private int id;
	
@NotBlank(message="firstName  is required")	
	private String firstName;
	
@NotBlank(message="Last name is required")
	private String lastName;
	
@NotBlank(message="email is required")
	private String email;
	
	private String phuneNumber;
	
	@Size(max=500,message="max character is alloed 500")
	private String address;
		
	boolean active;	
	
	


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String emial) {
		this.email = emial;
	}

	public String getPhuneNumber() {
		return phuneNumber;
	}

	public void setPhuneNumber(String phuneNumber) {
		this.phuneNumber = phuneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}


}
