package com.g3.elis.controller.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.g3.elis.model.EnrolledCourse;
import com.g3.elis.model.EnrolledMaterial;
import com.g3.elis.model.EnrolledModule;
import com.g3.elis.model.User;
import com.g3.elis.security.LoginUserDetail;
import com.g3.elis.service.EnrolledMaterialService;
import com.g3.elis.service.EnrolledModuleService;
import com.g3.elis.service.EnrolledCourseService;

@Controller
@RequestMapping("/student")
public class StudentCourseResumeController 
{
	@Autowired
	private EnrolledCourseService enrolledCourseService;
	
	@Autowired
	private EnrolledModuleService enrolledModuleService;
	
	@Autowired
	private EnrolledMaterialService enrollMaterialService;
	
	@GetMapping("/student-course-resume")
	public String studentCourseResume(Authentication authentication,Model model) 
	{
		LoginUserDetail userDetail = (LoginUserDetail) authentication.getPrincipal();
		User user = userDetail.getUser();
		List<EnrolledCourse> enrolledCourseList = enrolledCourseService.getAllEnrolledCourseByUserId(user.getId());
		for(EnrolledCourse enrolledCourse : enrolledCourseList)
		{
			for(EnrolledModule enrolledModule : enrolledCourse.getEnrolledModules())
			{
				enrolledModuleService.setStatusToTrue(enrolledModule.getId());
			}
			enrolledCourseService.setStatusToTrue(enrolledCourse.getId());
		}
		model.addAttribute("enrolledCourseList",enrolledCourseList);
		model.addAttribute("content","student/student-course-resume");
		return "/student/student-layout";
	}
	
	@GetMapping("/student-course-resume/view-material")
	public String studentViewMaterial(@RequestParam(name ="enrollMaterialId")int enrollMaterialId,
									  Model model)
	{
		model.addAttribute("enrolledMaterial",enrollMaterialService.getEnrolledMaterialByEnrolledMaterialId(enrollMaterialId));
		return "/student/student-course-material";
	}
	
	@GetMapping("/student-course-resume/complete-material")
	public String studentCompleteMaterial(@RequestParam(name ="enrollMaterialId")int enrollMaterialId,
										   Model model)
	{
		enrollMaterialService.setStatusToTrue(enrollMaterialId);
		model.addAttribute("content","student/student-course-resume");
		return "redirect:/student/student-course-resume";
	}
}
