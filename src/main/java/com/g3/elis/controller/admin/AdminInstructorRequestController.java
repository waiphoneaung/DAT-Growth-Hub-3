package com.g3.elis.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.g3.elis.service.CourseService;

@Controller
@RequestMapping("/admin")
public class AdminInstructorRequestController 
{
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/admin-instructor-request")
	public String adminInstructorRequest(Model model) 
	{
		model.addAttribute("courses",courseService.getAllPendingCourse());
		model.addAttribute("content", "admin/admin-instructor-request");
		return "/admin/admin-layout";
	}
}
