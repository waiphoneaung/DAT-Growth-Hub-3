package com.g3.elis.controller.instructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/instructor")
public class InstructorController {
	
	@GetMapping("/instructor-create-course")
	public String instructor_create_course(Model model) {
		
		model.addAttribute("currentPage", "instructor-manage-course");
		return "instructor/instructor-create-course";
	}
	
	@GetMapping("/instructor-quiz")
	public String instructor_quiz(Model model) {
		
		return "instructor/instructor-quiz";
	}
	
	@GetMapping("/blog-detail")
	public String blogDetail()
	{
		return "/authenticated-user/blog-detail";
	}
	
	@GetMapping("/blog_detail")
	public String BlogDetail(Model model) {
		
		return "/authenticated-user/blog-detail";
	}
	
}

