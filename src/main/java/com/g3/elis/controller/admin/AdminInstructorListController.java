package com.g3.elis.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.g3.elis.model.User;
import com.g3.elis.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminInstructorListController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/admin-instructor-list")
	public String adminInstructorList(Model model)
	{
		List<User> users = userService.getAllInstructors();
		model.addAttribute("users",users);
		model.addAttribute("content","admin/admin-instructor-list");
		return "/admin/admin-layout";
	}
}
