package com.g3.elis.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.g3.elis.service.CourseService;

@Controller
@RequestMapping("/admin")
public class AdminInstructorRequestController 
{
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/admin-instructor-request")
	public String adminInstructorRequest(@RequestParam(value = "search", required = false) String searchQuery, Model model) {
	    if (searchQuery != null && !searchQuery.isEmpty()) {
	        model.addAttribute("courses", courseService.searchPendingCourses(searchQuery));
	    } else {
	        model.addAttribute("courses", courseService.getAllPendingCourse());
	    }
	    model.addAttribute("content", "admin/admin-instructor-request");
	    return "/admin/admin-layout";
	}

}
