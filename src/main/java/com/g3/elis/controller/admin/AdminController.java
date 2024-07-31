package com.g3.elis.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController{
	@GetMapping("/admin-dashboard")
	public String home(Model model) {
		model.addAttribute("content","admin/admin-main");
		return "/admin/admin-layout";
	}
}