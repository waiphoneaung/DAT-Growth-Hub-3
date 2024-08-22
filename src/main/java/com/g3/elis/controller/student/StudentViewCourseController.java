package com.g3.elis.controller.student;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.g3.elis.model.Course;
import com.g3.elis.service.CourseService;

@Controller
@RequestMapping("/student")
public class StudentViewCourseController {
	@Autowired
	private CourseService courseService;

	@GetMapping("/student-view-allcourses")
	public String adminCourseList(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size,
			@RequestParam(name = "keyword", required = false) String keyword) {

		Page<Course> coursePage;
		if (keyword != null && !keyword.isEmpty()) {
			coursePage = courseService.searchCoursesByTitle(keyword, PageRequest.of(page, size));
		} else {
			coursePage = courseService.getPaginatedCourses(PageRequest.of(page, size));
		}

		// Fetch module counts using the service method
		Map<Integer, Long> courseModuleCounts = courseService.countCourseModulesForCourses(coursePage);

		int totalCourses = (int) coursePage.getTotalElements();
		int activatedCourse = (int) coursePage.getContent().stream()
				.filter(course -> "Activated".equalsIgnoreCase(course.getStatus())).count();
		int pendingCourse = (int) coursePage.getContent().stream()
				.filter(course -> "Pending".equalsIgnoreCase(course.getStatus())).count();

		model.addAttribute("totalCourse", totalCourses);
		model.addAttribute("activatedCourse", activatedCourse);
		model.addAttribute("pendingCourse", pendingCourse);
		model.addAttribute("courses", coursePage.getContent());
		model.addAttribute("courseModuleCounts", courseModuleCounts);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", coursePage.getTotalPages());

		return "/student/student-view-allcourses";
	}
}
