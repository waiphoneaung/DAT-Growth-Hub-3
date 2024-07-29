package com.g3.elis.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	
	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("content","user/main");
		return "/user/layout";
	}
}
