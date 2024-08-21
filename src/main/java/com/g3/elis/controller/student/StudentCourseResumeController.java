package com.g3.elis.controller.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.g3.elis.model.EnrolledCourse;
import com.g3.elis.model.User;
import com.g3.elis.security.LoginUserDetail;
import com.g3.elis.service.EnrolledCourseService;

@Controller
@RequestMapping("/student")
public class StudentCourseResumeController 
{
	@Autowired
	private EnrolledCourseService enrolledCourseService;
	
	@GetMapping("/student-course-resume")
	public String studentCourseResume(Authentication authentication,Model model) 
	{
		LoginUserDetail userDetail = (LoginUserDetail) authentication.getPrincipal();
		User user = userDetail.getUser();
		List<EnrolledCourse> enrolledCourseList = enrolledCourseService.getAllEnrolledCourseByUserId(user.getId());
		model.addAttribute("enrolledCourseList",enrolledCourseList);
		model.addAttribute("content","student/student-course-resume");
		return "/student/student-layout";
	}
}
