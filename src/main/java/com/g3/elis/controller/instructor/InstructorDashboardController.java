package com.g3.elis.controller.instructor;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/instructor")
public class InstructorDashboardController {

		
	
	@GetMapping("/instructor-dashboard")
	public String home( Model model) {

		
		model.addAttribute("currentPage", "instructor-dashboard");
		model.addAttribute("content", "instructor/instructor-dashboard");
		return "instructor/instructor-layout";

	}
 
}
