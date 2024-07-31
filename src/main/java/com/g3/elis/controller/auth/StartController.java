package com.g3.elis.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.g3.elis.service.RoleService;
import com.g3.elis.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class StartController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;

	@GetMapping("/")
	public String index(HttpServletRequest  request,Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication!= null) 
		{
			return "redirect:/home";
		} else 
		{
			return "redirect:/auth/login";
		}
	}
}