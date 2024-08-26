package com.g3.elis.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.g3.elis.util.EmailSenderService;
import com.g3.elis.util.OtpService;

import jakarta.validation.Valid;

@Controller
public class ForgotPasswordController {
    @Autowired
    private OtpService otpService;
    

    @PostMapping("/auth/forgot-password")
    public String sendOtp(@Valid @RequestParam("email") String email, Model model) {
        String otp = otpService.generateOtp(email);
        if (otp == null) {
            // User not found, no email sent
            model.addAttribute("error", "No user found with the provided email address.");
           
            return "/auth/forgot-password"; 
        }

        return "redirect:/auth/reset-password?email=" + email;
    }
}
