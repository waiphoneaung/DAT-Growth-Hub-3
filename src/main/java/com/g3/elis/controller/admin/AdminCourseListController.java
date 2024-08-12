package com.g3.elis.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.g3.elis.model.Course;
import com.g3.elis.service.CourseService;

@Controller
@RequestMapping("/admin")
public class AdminCourseListController {

	@Autowired
	private CourseService courseService;
	
	@GetMapping("/admin-course-list")
	public String adminCourseList(Model model)
	{
		List<Course>courseList = courseService.getAllCourse();
		int totalCourse = courseList.size();
		int activatedCourse = 0;
		int pendingCourse = 0;
		for(Course course : courseList)
		{
			if(course.getStatus().equalsIgnoreCase("Pending"))
			{
				pendingCourse++;
			}
			if(course.getStatus().equalsIgnoreCase("Live"))
			{
				activatedCourse++;
			}
		}
		model.addAttribute("totalCourse",totalCourse);
		model.addAttribute("activatedCourse",activatedCourse);
		model.addAttribute("pendingCourse",pendingCourse);
		model.addAttribute("courses",courseList);
		model.addAttribute("content","admin/admin-course-list");
		return "/admin/admin-layout";
	}
	@GetMapping("/admin-course-detail")
	public String adminCourseDetail(Model model)
	{
		model.addAttribute("content","admin/admin-course-detail");
		return "/admin/admin-layout";
	}
	@GetMapping("/admin-edit-course-detail")
	public String adminEditCourseDetail(Model model)
	{
		model.addAttribute("content","admin/admin-edit-course-detail");
		return "/admin/admin-layout";
	}
}
