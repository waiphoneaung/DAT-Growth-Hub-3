package com.g3.elis.controller.student;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {
	@GetMapping("/student-dashboard")
	public String home(Model model) {
		model.addAttribute("content","student/student-main");
		return "/student/student-layout";
	}
}
