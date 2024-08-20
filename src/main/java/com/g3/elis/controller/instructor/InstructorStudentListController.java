package com.g3.elis.controller.instructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.g3.elis.model.User;
import com.g3.elis.security.LoginUserDetail;
import com.g3.elis.service.EnrolledCourseService;

@Controller
@RequestMapping("/instructor")
public class InstructorStudentListController 
{
	@Autowired
	private EnrolledCourseService enrolledCourseService;
	
	@GetMapping("/instructor-studentlist")
	public String instructor_studentlist(Authentication authentication,Model model) 
	{
		LoginUserDetail userDetail = (LoginUserDetail) authentication.getPrincipal();
		User user = userDetail.getUser();
		model.addAttribute("enrolledCourses",enrolledCourseService.getAllEnrolledCourseByUserId(user.getId()));
		model.addAttribute("currentPage", "instructor-studentlist");
		model.addAttribute("content", "instructor/instructor-studentlist");
		return "instructor/instructor-layout";
	}
	
}
