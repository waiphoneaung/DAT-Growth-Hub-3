package com.g3.elis.controller.instructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.g3.elis.dto.report.CourseProgress;
import com.g3.elis.service.ReportService;

@Controller
@RequestMapping("/instructor")
public class InstructorReportController 
{
	@Autowired
	private ReportService reportService;
	
	@GetMapping("/instructor-report")
	public String home(@RequestParam(name = "courseId")int courseId,Model model) 
	{
		List<CourseProgress> reports = reportService.generateCourseProgressReport(courseId);
		model.addAttribute("reports",reports);
		model.addAttribute("currentPage", "instructor-dashboard");
		model.addAttribute("content", "instructor/instructor-report");
		return "instructor/instructor-layout";
	}
}	
