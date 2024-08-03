package com.g3.elis.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public String loginPage() {
        return "user/sign-in";
    }
    
    @GetMapping("/forgot-password")
    public String showForgotPasswordPage() {
        return "/user/forgot-password";
    }
    
    @GetMapping("/reset-password")
    public String showResetPasswordPage(@RequestParam("email") String email, Model model) {
        model.addAttribute("email", email);
        return "/user/reset-password";
    }
    
    @GetMapping("/access-denied")
    public String deniedPage()
    {
    	return "redirect:/user/error404";
    }
    
    
}