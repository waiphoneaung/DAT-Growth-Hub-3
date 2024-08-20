package com.g3.elis.controller.student;

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
@RequestMapping("/student")
public class StudentCourseListController {

	@Autowired
	private CourseService courseService;
	
	@GetMapping("/student-course-list")
	public String studentCourseList(Model model)
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
		model.addAttribute("content","student/student-course-list");
		return "/student/student-layout";
	}
	@GetMapping("/student-course-list/approve")
	public String studentApproveRequest(@RequestParam(name="courseId")int courseId)
	{
		courseService.editCourse(courseId, "Activated");
		return "redirect:/student/student-course-list";
	}
	@GetMapping("/student-course-list/reject")
	public String studentRejectRequest(@RequestParam(name="courseId")int courseId)
	{
		courseService.editCourse(courseId, "Rejected");
		return "redirect:/student/student-course-list";
	}
	@GetMapping("/student-course-detail")
	public String studentCourseDetail(Model model)
	{
		model.addAttribute("content","student/student-course-detail");
		return "/student/student-layout";
	}
	@GetMapping("/student-view-allcourses")
	public String studentViewAllcourses(Model model)
	{
		model.addAttribute("content","student/student-view-allcourses");
		return "/student/student-layout";
	}
}
