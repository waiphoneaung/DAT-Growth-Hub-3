package com.g3.elis.controller.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.g3.elis.dto.report.CoursePerformance;
import com.g3.elis.dto.report.CourseProgress;
import com.g3.elis.model.Course;
import com.g3.elis.model.EnrolledCourse;
import com.g3.elis.model.User;
import com.g3.elis.model.UserLog;
import com.g3.elis.repository.UserRepository;
import com.g3.elis.security.LoginUserDetail;
import com.g3.elis.service.CourseService;
import com.g3.elis.service.EnrolledCourseService;
import com.g3.elis.service.ReportService;
import com.g3.elis.service.UserLogService;
import com.g3.elis.util.InputFileService;

@Controller
@RequestMapping("/admin")
public class AdminDashboardController 
{
	@Autowired
	private EnrolledCourseService enrolledCourseService;
	
	@Autowired
	private InputFileService inputFileService;
	
	@Autowired
	private ReportService reportService;
	
	@Autowired 
	private UserLogService userLogService;
	
	@GetMapping("/admin-dashboard")
	public String home(@RequestParam(required = false, defaultValue = "all") String timeRange,Authentication authentication,Model model) 
	{
		int completeCourses = 0;
		int courseInProgress = 0;
		
		
		for(EnrolledCourse enrolledCourse : enrolledCourseService.getAllEnrolledCourse())
		{
			if(enrolledCourse.isCompleteStatus() == true) completeCourses++;
			else courseInProgress++;
			
		}
		List<Integer> apexChartData = reportService.generateEnrolledUserByMonthDataForApexChart();
		List<String> apexChartMonths = new ArrayList<String>(Arrays.asList("Jan", "Feb", "Mar", "Apr","May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"));
		List<Integer> trafficChartData = reportService.generateGradesResultsForTrafficChart();
		List<CoursePerformance> reports = reportService.generateCoursePerformanceReport();
		List<UserLog> logs;
		
		
		switch(timeRange) {
		case "week" :
			logs = userLogService.findLogsInLastWeek();
			break;
		case "month" :
			logs = userLogService.findLogsInLastMonth();
			break;
		case "year" :
			logs = userLogService.findLogsInLastYear();
			break;
			default:
				logs = userLogService.getUserLogs();
		}
		
		model.addAttribute("trafficChartData",trafficChartData);
		model.addAttribute("apexChartData",apexChartData);
		model.addAttribute("apexChartMonths",apexChartMonths);
		model.addAttribute("reports",reports);
		model.addAttribute("enrolledCourses",enrolledCourseService.getAllEnrolledCourse().size());
		model.addAttribute("completeCourses",completeCourses);
		model.addAttribute("courseInProgress",courseInProgress);
		model.addAttribute("content", "admin/admin-dashboard");
    	model.addAttribute("logs", logs);
    	model.addAttribute("timeRange", timeRange);// To keep the selected option in the dropdown

		return "/admin/admin-layout";
	}
	@GetMapping("/admin-dashboard/generate-course-performance-report")
	public String generateReport(Model model)
	{
		List<CoursePerformance> reports = reportService.generateCoursePerformanceReport();
		inputFileService.generateCoursePerformanceExcelReportFile(reports);
		model.addAttribute("content", "admin/admin-dashboard");
		return "redirect:/admin/admin-dashboard";
	}
}
