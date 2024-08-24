package com.g3.elis.controller.instructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.g3.elis.dto.report.QuizPerformance;
import com.g3.elis.model.EnrolledAssignment;
import com.g3.elis.security.LoginUserDetail;
import com.g3.elis.service.EnrolledAssignmentService;
import com.g3.elis.service.ReportService;

@Controller
@RequestMapping("/instructor")
public class InstructorQuizResultController 
{
	@Autowired
	private ReportService reportService;
	
	@GetMapping("/instructor-quiz-result")
	public String instructor_quiz_result(Authentication authentication,Model model) 
	{
		LoginUserDetail userDetail = (LoginUserDetail) authentication.getPrincipal();
		List<QuizPerformance> reports =reportService.generateQuizPerformanceReport(userDetail.getUser().getId());
		model.addAttribute("reports",reports);
		model.addAttribute("currentPage", "instructor-quiz-result");
		model.addAttribute("content", "instructor/instructor-quiz-result");
		return "instructor/instructor-layout";
	}
}
