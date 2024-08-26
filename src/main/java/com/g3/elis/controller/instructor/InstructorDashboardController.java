package com.g3.elis.controller.instructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.g3.elis.dto.report.CourseProgress;
import com.g3.elis.model.User;
import com.g3.elis.repository.CourseRepository;
import com.g3.elis.security.LoginUserDetail;
import com.g3.elis.service.CourseService;
import com.g3.elis.service.ReportService;

@Controller
@RequestMapping("/instructor")
public class InstructorDashboardController 
{

	@Autowired
	private CourseService courseService;
	
	@GetMapping("/instructor-dashboard")
	public String home(Authentication authentication, Model model) {
	    LoginUserDetail loginUser = (LoginUserDetail) authentication.getPrincipal();
	    User user = loginUser.getUser();

	    
	    model.addAttribute("currentPage", "instructor-dashboard");
	    model.addAttribute("content", "instructor/instructor-dashboard");
	    return "instructor/instructor-layout";
	}
 
}
