package com.g3.elis.controller.instructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.g3.elis.model.User;
import com.g3.elis.security.LoginUserDetail;
import com.g3.elis.service.CourseService;

@Controller
@RequestMapping("/instructor")
public class InstructorManageCourseController 
{	
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/instructor-manage-course")
	public String instructor_manage_course(Model model,Authentication authentication) 
	{
		LoginUserDetail userDetail = (LoginUserDetail) authentication.getPrincipal();
		User user = userDetail.getUser();;
		model.addAttribute("courses",courseService.getAllCourseByUserId(user.getId()));
		model.addAttribute("currentPage", "instructor-course-list");
		model.addAttribute("content", "instructor/instructor-course-list");
		return "instructor/instructor-layout";
	}
}
