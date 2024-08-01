package com.g3.elis.controller.student;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {
	@GetMapping("/student-dashboard")
	public String home(Model model) {
		model.addAttribute("content","student/student-dashboard");
		return "student/student-layout";
	}
	@GetMapping("/student-view-blog")
	public String studentViewBlog(Model model) {
		model.addAttribute("content","student/student-view-blog");
		return "student/student-view-blog";
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
	public String instructorList(Model model) {
		model.addAttribute("content","student/instructor-list");
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
	
	@GetMapping("/student-edit-profile")
	public String studentEditProfile(Model model) {
		model.addAttribute("content","student/student-edit-profile");
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
	
	@GetMapping("/forum.html")
	public String Forum(Model model) {
		return "/authenticated-user/forum";
	}
}
