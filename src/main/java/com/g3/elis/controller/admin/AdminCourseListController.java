package com.g3.elis.controller.admin;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
			if(course.getStatus().equalsIgnoreCase("Activated"))
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
	@GetMapping("/admin-course-list/approve")
	public String adminApproveRequest(@RequestParam(name="courseId")int courseId)
	{
		courseService.editCourse(courseId, "Activated");
		return "redirect:/admin/admin-course-list";
	}
	
	@GetMapping("/admin-course-list/reject")
	public String adminRejectRequest(@RequestParam(name="courseId")int courseId)
	{
		courseService.editCourse(courseId, "Rejected");
		return "redirect:/admin/admin-course-list";
	}
	
	@GetMapping("/admin-course-list/delete")
	public String adminDeleteCourse(@RequestParam(name="courseId")int courseId) throws IOException
	{
		courseService.deleteCourse(courseId);
		return "redirect:/admin/admin-course-list";
	}
	
	@GetMapping("/admin-course-list/cancel")
	public String adminCancelReject(@RequestParam(name="courseId")int courseId)
	{
		courseService.editCourse(courseId, "Pending");
		return "redirect:/admin/admin-course-list";
	}
	
	@GetMapping("/admin-course-detail")
	public String adminCourseDetail(Model model)
	{
		return "/admin/admin-course-detail";
	}
}