package com.g3.elis.controller.instructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.g3.elis.service.EnrolledAssignmentService;

@Controller
@RequestMapping("/instructor")
public class InstructorQuizResultController 
{
	@Autowired
	private EnrolledAssignmentService enrolledAssignmentService;
	
	@GetMapping("/instructor-quiz-result")
	public String instructor_quiz_result(Model model) 
	{
		
		model.addAttribute("currentPage", "instructor-quiz-result");
		model.addAttribute("content", "instructor/instructor-quiz-result");
		return "instructor/instructor-layout";
	}
}
