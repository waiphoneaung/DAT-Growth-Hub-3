package com.g3.elis.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.g3.elis.util.EmailSenderService;
import com.g3.elis.util.OtpService;

@Controller
public class ForgotPasswordController {
    @Autowired
    private OtpService otpService;
    
    @Autowired
    private EmailSenderService emailService;

    

    @PostMapping("/auth/forgot-password")
    public String sendOtp(@RequestParam("email") String email, Model model) {
        String otp = otpService.generateOtp(email);
        emailService.sendOtpEmail(email, otp);
        model.addAttribute("email", email);
        return "redirect:/auth/reset-password?email=" + email;
    }
}
