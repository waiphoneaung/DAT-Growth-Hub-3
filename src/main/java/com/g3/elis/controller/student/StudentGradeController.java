package com.g3.elis.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.g3.elis.security.LoginUserDetail;
import com.g3.elis.service.GradeService;

@Controller
@RequestMapping("/student")
public class StudentGradeController 
{
	@Autowired
	private GradeService gradeService;
	
	@GetMapping("/student-grade")
	public String studentGrade(Authentication authentication,Model model) 
	{
		LoginUserDetail userDetail = (LoginUserDetail) authentication.getPrincipal();
		model.addAttribute("grades",gradeService.getAllGradeByStudentId(userDetail.getUser().getId()));
		model.addAttribute("content","student/student-grade");
		return "/student/student-layout";
	}
	
}
