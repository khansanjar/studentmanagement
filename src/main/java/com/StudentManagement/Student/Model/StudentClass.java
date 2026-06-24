package com.StudentManagement.Student.Model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;

@Entity
public class StudentClass {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)

	private int id;
	
	@Column(nullable=false)
	private String firstName;
	
	@Column(nullable=false)
	private String lastName;
	
	@Column(nullable=false)
	
	private String email;
	
	private String phuneNumber;
	
	@Column(nullable=false,length=500)
private String address;
		
	boolean active=true;	
	
	@Column(nullable=false,updatable=false)
	 private LocalDateTime createdAt;

	@PrePersist
	public void onCreate() {
		createdAt=LocalDateTime.now();
	}
	@OneToMany(mappedBy="student",cascade=CascadeType.ALL, orphanRemoval=true)
	private Set<Enrollment> enrollments=new HashSet<>();

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

	public void setEmail(String email) {
		this.email = email;
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Set<Enrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(Set<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}
	
}
