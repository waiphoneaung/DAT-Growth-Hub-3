package com.g3.elis.controller.instructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/instructor")
public class InstructorController {
	
	
	@GetMapping({"/instructor-dashboard","/"} )
	public String home(Model model) {
		
		 model.addAttribute("currentPage", "instructor-dashboard");
		model.addAttribute("content", "instructor/instructor-dashboard");
		return "instructor/instructor-layout";
	}
	
	
	@GetMapping("/instructor-manage-course")
	public String instructor_manage_course(Model model) {
		
		model.addAttribute("currentPage", "instructor-manage-course");
		model.addAttribute("content", "instructor/instructor-manage-course");
		return "instructor/instructor-layout";
	}
	
	@GetMapping("/instructor-create-course")
	public String instructor_create_course(Model model) {
		
		model.addAttribute("currentPage", "instructor-manage-course");
//		model.addAttribute("content", "instructor/instructor-create-course");
		return "instructor/instructor-create-course";
	}
	
	@GetMapping("/instructor-quiz")
	public String instructor_quiz(Model model) {
		
		return "instructor/instructor-quiz";
	}
	
	@GetMapping("/instructor-quiz-result")
	public String instructor_quiz_result(Model model) {
		
		model.addAttribute("currentPage", "instructor-quiz-result");
		model.addAttribute("content", "instructor/instructor-quiz-result");
		return "instructor/instructor-layout";
	}
	
	@GetMapping("/instructor-studentlist")
	public String instructor_studentlist(Model model) {
		
		model.addAttribute("currentPage", "instructor-studentlist");
		model.addAttribute("content", "instructor/instructor-studentlist");
		return "instructor/instructor-layout";
	}
	
	@GetMapping("/forum")
	public String forum(Model model) {
		 
		model.addAttribute("currentPage", "forum");
		return "instructor/forum";
	}
	
	@GetMapping("/instructor-view-blog")
	public String instructor_view_blog(Model model) {
		
		model.addAttribute("currentPage", "instructor-view-blog");
		model.addAttribute("content", "instructor/instructor-view-blog");
		return "instructor/instructor-layout";
	}
	
	@GetMapping("/instructor-edit-profile")
	public String instructor_edit_profile(Model model) {
		
		model.addAttribute("currentPage", "instructor-edit-profile");
		model.addAttribute("content", "instructor/instructor-edit-profile");
		return "instructor/instructor-layout";
	}
}
