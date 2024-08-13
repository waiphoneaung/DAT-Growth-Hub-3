package com.g3.elis.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"","/"})
public class StartController {

	@GetMapping({"","/"})
	public String redirectToHome()
	{
		return "redirect:/user/home";
	}
}