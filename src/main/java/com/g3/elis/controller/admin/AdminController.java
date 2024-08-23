package com.g3.elis.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@GetMapping("/admin-certificate-design")
	public String adminCertificateDesign(Model model) {
		model.addAttribute("content", "admin/admin-certificate-design");
		return "/admin/admin-layout";
	}

	@GetMapping("/admin-setting")
	public String adminSetting(Model model) {
		model.addAttribute("content", "admin/admin-setting");
		return "/admin/admin-layout";
	}

	@GetMapping("/admin-quiz")
	public String adminQuiz(Model model) {
		return "/admin/admin-quiz";
	}
	
}