package com.g3.elis.controller.instructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.g3.elis.security.LoginUserDetail;
import com.g3.elis.service.EnrolledCourseService;
import com.g3.elis.service.ReportService;

@Controller
@RequestMapping("/instructor")
public class InstructorDashboardController {

	@Autowired
	private ReportService reportService;
	
	@Autowired
	private EnrolledCourseService enrolledCourseService;

	@GetMapping("/instructor-dashboard")
	public String home(Authentication authentication,Model model) 
	{
		LoginUserDetail userDetail = (LoginUserDetail) authentication.getPrincipal();
		List<Integer> apexChartData = reportService.generateEnrolledUserByMonthDataForApexChart(userDetail.getUser().getId());
		List<String> apexChartMonths = new ArrayList<String>(Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"));
		model.addAttribute("enrolledCourse",enrolledCourseService.getAllEnrolledCourseByInstructorId(userDetail.getUser().getId()));
		model.addAttribute("apexChartData", apexChartData);
		model.addAttribute("apexChartMonths", apexChartMonths);
		model.addAttribute("currentPage", "instructor-dashboard");
		model.addAttribute("content", "instructor/instructor-dashboard");
		return "instructor/instructor-layout";

	}

}
