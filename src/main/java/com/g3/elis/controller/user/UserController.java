package com.g3.elis.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.g3.elis.model.Course;
import com.g3.elis.model.CourseCategory;
import com.g3.elis.service.BlogPostService;
import com.g3.elis.service.CourseCategoryService;
import com.g3.elis.service.CourseService;
import com.g3.elis.service.UserService;

@Controller
@RequestMapping({ "/user", "/", "" })
public class UserController {


	@Autowired
	private BlogPostService blogPostService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private CourseCategoryService courseCategoryService;

	@Autowired
	private CourseService courseService;

	@GetMapping("/home")
	public String home(Model model) {
	    // Add counts to the model
	    model.addAttribute("courseCount", courseService.countAllCourses());
	    model.addAttribute("instructorCount", userService.countAllInstructor());
	    model.addAttribute("studentCount", userService.countAllStudent());

	    // Get top 3 most enrolled course categories
	    List<CourseCategory> top3Categories = courseCategoryService.findTop3MostEnrolledCategories();
	    model.addAttribute("top3Categories", top3Categories);

	    // Get courses for each of the top 3 categories
	    Map<Integer, List<Course>> coursesByCategory = new HashMap<>();
	    for (CourseCategory category : top3Categories) {
	        List<Course> courses = courseService.findCoursesByCategory(category.getId());
	        coursesByCategory.put(category.getId(), courses);
	    }
	    model.addAttribute("coursesByCategory", coursesByCategory);

	    model.addAttribute("content", "user/main");
	    return "/user/layout";
	}



	@GetMapping("/contactus")
	public String contactUs(Model model) {
		model.addAttribute("content", "user/contactus");
		return "/user/layout";
	}

	@GetMapping("/about")
	public String about(Model model) {
		model.addAttribute("content", "user/about");
		return "/user/layout";
	}

	public String courseList(Model model) {
		model.addAttribute("content", "user/courses");
		return "/user/layout";
	}

	@GetMapping("/error404")
	public String error404(Model model) {
		model.addAttribute("content", "user/error404");
		return "/user/layout";
	}

	@GetMapping("/blog_detail")
	public String BlogDetail(Model model) {

		return "/user/blog-detail";
	}

}
