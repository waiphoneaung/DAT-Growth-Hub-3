package com.g3.elis.controller.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.g3.elis.model.Profile;
import com.g3.elis.model.User;
import com.g3.elis.service.UserService;

@Controller
@RequestMapping("/student")
public class StudentController {
	 
	@Autowired
	UserService userService;
	
	@GetMapping("/student-dashboard")
	public String home(Model model) {
		model.addAttribute("content","student/student-dashboard");
		return "/student/student-layout";
	}

	
	@GetMapping("/blog-detail")
	public String blogDetail()
	{
		return "/authenticated-user/blog-detail";
	}
	
	
	@GetMapping("/student-course-list")
	public String studentCourseList(Model model) {
		model.addAttribute("content","student/student-course-list");
		return "/student/student-layout";
	}
	
	@GetMapping("/student-view-allcourses")
	public String studentAllCourse(Model model) {
		
		return "/student/student-view-allcourses";
	}
	

	@GetMapping("/instructor-list")
	public String instructorList(@RequestParam(value = "page", defaultValue = "0") int page, Model model) {
	    Pageable pageable = PageRequest.of(page, 8);
	    Page<User> userPage = userService.getAllInstructors(pageable);
	    model.addAttribute("users", userPage.getContent());
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", userPage.getTotalPages());
	    model.addAttribute("content", "student/instructor-list");
	    return "/student/student-layout";
	}


	
	@GetMapping("/student-certificate")
	public String studentCertificate(Model model) {
		model.addAttribute("content","student/student-certificate");
		return "/student/student-layout";
	}
	
	@GetMapping("/student-grade")
	public String studentGrade(Model model) {
		model.addAttribute("content","student/student-grade");
		return "/student/student-layout";
	}
	
	
	
	@GetMapping("/student-course-resume")
	public String studentCourseResume(Model model) {
		model.addAttribute("content","student/student-course-resume");
		return "/student/student-layout";
	}
	
	@GetMapping("/student-quiz")
	public String studentQuiz(Model model) {
		
		return "/student/student-quiz";
	}
	
	@GetMapping("/blog_detail")
	public String BlogDetail(Model model) {
		
		return "/authenticated-user/blog-detail";
	}
	
	
	
}
