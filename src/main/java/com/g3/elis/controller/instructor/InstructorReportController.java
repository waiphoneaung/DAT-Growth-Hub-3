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
import com.g3.elis.service.EnrolledCourseService;
import com.g3.elis.service.ReportService;
import com.g3.elis.util.InputFileService;

@Controller
@RequestMapping("/instructor")
public class InstructorReportController 
{
	@Autowired
	private ReportService reportService;
	
	@Autowired
	private InputFileService inputFileService;
	
	@Autowired
	private EnrolledCourseService enrolledCourseService;
	
	@GetMapping("/instructor-report")
	public String home(@RequestParam(name = "courseId")int courseId,Model model) 
	{
		List<CourseProgress> reports = reportService.generateCourseProgressReport(courseId);
		model.addAttribute("reports",reports);
		model.addAttribute("currentPage", "instructor-dashboard");
		model.addAttribute("content", "instructor/instructor-report");
		model.addAttribute("courseId",courseId);
		return "instructor/instructor-layout";
	}
	@GetMapping("/instructor-report/generate-course-progress-report")
	public String generateReport(@RequestParam(name= "courseId")int courseId,Model model)
	{
		inputFileService.generateCourseProgressExcelReportFile(reportService.generateCourseProgressReport(courseId));
		List<CourseProgress> reports = reportService.generateCourseProgressReport(courseId); 
		model.addAttribute("reports", reports);
		model.addAttribute("courseId",courseId);
		model.addAttribute("enrolledCourses", enrolledCourseService.getAllEnrolledCourse().size());
		model.addAttribute("content", "admin/admin-report");
		return "redirect:/instructor/instructor-report?courseId="+courseId;
	}
}	
