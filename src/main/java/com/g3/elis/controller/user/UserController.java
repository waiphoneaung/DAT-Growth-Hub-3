package com.g3.elis.controller.user;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.g3.elis.service.BlogPostService;
import com.g3.elis.service.UserService;

@Controller
@RequestMapping({"/user","/",""})
public class UserController {
	

	@Autowired
    private BlogPostService blogPostService;
	
	@Autowired
	private UserService userService;

	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("content","user/main");
		return "/user/layout";
	}
	
//	@GetMapping("/courses")
//	public String courses(Model model) {
//		model.addAttribute("content","user/courses");
//		return "/user/layout";
//	}
	
	@GetMapping("/contactus")
	public String contactUs(Model model) {
		model.addAttribute("content","user/contactus");
		return "/user/layout";
	}
	
	@GetMapping("/about")
	public String about(Model model) {
		model.addAttribute("content","user/about");
		return "/user/layout";
	}
	



	public String courseList(Model model) {
		model.addAttribute("content","user/courses");
		return "/user/layout";
	}
	@GetMapping("/error404")
	public String error404(Model model) {
		model.addAttribute("content","user/error404");
		return "/user/layout";
	}
	
	@GetMapping("/blog_detail")
	public String BlogDetail(Model model) {
		
		return "/user/blog-detail";
	}
	
	
	
//	@GetMapping("/course/{id}")
//   public String getCourseDetail(@PathVariable("id") Long id, Model model, Principal principal) {
//	    // Fetch course details and user enrollment status
//    boolean isEnrolled = userService.isUserEnrolled(principal.getName(), id);
//	    model.addAttribute("isEnrolled", isEnrolled);
//	    return "course-detail";
//	}

	
	
}
