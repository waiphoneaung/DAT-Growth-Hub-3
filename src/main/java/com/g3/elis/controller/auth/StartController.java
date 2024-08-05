package com.g3.elis.controller.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class StartController {

	@GetMapping("/")
	public String index(HttpServletRequest  request,Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication== null) 
		{
			return "redirect:/auth/login";
		}
		return "redirect:/auth/login";
	}
}