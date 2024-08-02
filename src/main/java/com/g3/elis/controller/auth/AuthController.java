package com.g3.elis.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public String loginPage() {
        return "user/sign-in";
    }
    @GetMapping("/access-denied")
    public String deniedPage()
    {
    	return "redirect:/user/error404";
    }
}