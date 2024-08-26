package com.g3.elis.controller.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.g3.elis.model.Achievement;
import com.g3.elis.model.Course;
import com.g3.elis.model.User;
import com.g3.elis.security.LoginUserDetail;
import com.g3.elis.service.AchievementService;
import com.g3.elis.service.EnrolledCourseService;

@Controller
@RequestMapping("/student")
public class StudentCertificateController 
{
	@Autowired
	private EnrolledCourseService enrolledCourseService;
	
	@Autowired
	private AchievementService achievementService;
	
	@GetMapping("/student-certificate")
	public String studentCertificate(Authentication authentication,Model model) 
	{
		LoginUserDetail userDetail = (LoginUserDetail) authentication.getPrincipal();
		List<Achievement> achievementList = userDetail.getUser().getAchievement();
		int bronze = 0,silver = 0 ,gold = 0;
		for(int i = 0; i < achievementList.size(); i++)
		{
			if(i % 5 == 0) bronze++;
			if(i % 10 == 0) silver++;
			if(i % 20 == 0) gold++;
		}
		model.addAttribute("bronze",bronze);
		model.addAttribute("silver",silver);
		model.addAttribute("gold",gold);
		model.addAttribute("certificates",achievementService.getAllAchievement());
		model.addAttribute("content","student/student-certificate");
		return "/student/student-layout";
	}
	
	@GetMapping("/student-certificate/view-certificate")
	public String studentViewCertificate(@RequestParam(name = "certificateId")int certificateId,
										 Model model)
	{
		Achievement achievement = achievementService.getAchievementById(certificateId);
		model.addAttribute("certificate",achievement);
		return "/user/certificate";	
	}
	
	@GetMapping("/student-certificate/create-certificate")
	public String createCertificate(@RequestParam(name = "enrolledCourseId")int enrolledCourseId,
									Authentication authentication,Model model)
	{
		Course course = enrolledCourseService.getEnrolledCourseByEnrolledCourseId(enrolledCourseId).getCourses();
		LoginUserDetail userDetail = (LoginUserDetail) authentication.getPrincipal();
		User user = userDetail.getUser();
		achievementService.createCertificate(user.getId(),course.getId());
		model.addAttribute("enrolledCourse",enrolledCourseService.getEnrolledCourseByEnrolledCourseId(enrolledCourseId));
		return "/user/certificate";
	}
}
