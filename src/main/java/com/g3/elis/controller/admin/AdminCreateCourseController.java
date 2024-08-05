package com.g3.elis.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminCreateCourseController 
{
	
	@GetMapping("/admin-create-course")
	public String adminCreateCourse(Model model) 
	{
		
		return "/admin/admin-create-course";
	}
	@PostMapping("/admin-create-course")
	public String adminCreateCourse()
	{
		return "/admin/admin-create-course";
	}
}
