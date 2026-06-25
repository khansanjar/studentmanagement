package com.StudentManagement.Student.service.impl;


import java.text.Collator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.StudentManagement.Student.DTO.DashboardCountDTO;
import com.StudentManagement.Student.Repoistory.CourseRepo;
import com.StudentManagement.Student.Repoistory.EnrollmentRepo;
import com.StudentManagement.Student.Repoistory.StudentRepo;

@Service
public class DashBoardServiceImpl implements DashBoardService {

	private EnrollmentRepo enrollmentrepo;
	private final StudentRepo studentRepo;
	private final CourseRepo courserepo;


	public DashBoardServiceImpl(EnrollmentRepo enrollmentrepo,StudentRepo studentRepo,CourseRepo courserepo)
	{
		this.enrollmentrepo=enrollmentrepo;
		this.studentRepo=studentRepo;
		this.courserepo=courserepo;
	}
		
	@Override
	public DashboardCountDTO getDashBoardstats() {
			
		long totalstudent=studentRepo.count();
		long totalcourse=courserepo.count();
		
		String topcourse=getTopcourse();
		YearMonth month=YearMonth.now();
		LocalDateTime startDate=month.atDay(1).atStartOfDay();
		LocalDateTime endDate=month.atEndOfMonth().atTime(LocalTime.MAX);
		long enrolledstudent=enrollmentrepo.countDistinctStudentByEnrollDateBetween(startDate, endDate);
		
		DashboardCountDTO DTO=new DashboardCountDTO();
		DTO.setTopcourse(topcourse);
		DTO.setTotaalstudent(totalstudent);
		DTO.setTotalcourse(totalcourse);
		DTO.setTotalstudentenrolled(enrolledstudent);
		return DTO;
	}
	
	private String getTopcourse() {
	  return enrollmentrepo.findAll()
			  .stream()
			  .collect(Collectors.groupingBy(e->e.getCourse().getCoursecode(),Collectors.counting()))
			  .entrySet()
			  .stream()
			  .max(Map.Entry.comparingByValue())
			  .map(Map.Entry::getKey)
			  .orElse("N/A");
	}
	

}

















