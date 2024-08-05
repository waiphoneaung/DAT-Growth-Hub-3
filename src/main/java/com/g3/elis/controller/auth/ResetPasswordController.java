package com.g3.elis.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.g3.elis.util.OtpService;
import com.g3.elis.util.PasswordResetService;



@Controller
public class ResetPasswordController {
    @Autowired
    private OtpService otpService;
    
    @Autowired
    private PasswordResetService pwdResetService;
    

    @PostMapping("/auth/reset-password")
    public String resetPassword(@RequestParam("email") String email, 
                                @RequestParam("otp") String otp, 
                                @RequestParam("newPassword") String newPassword, 
                                Model model) {
        if (otpService.validateOtp(email, otp)) {
            pwdResetService.updatePassword(email, newPassword);
            return "redirect:/user/home";
        } else {
        	model.addAttribute("error", "Invalid OTP or OTP has expired.");
            model.addAttribute("email", email);  // Ensure email is preserved on error

            return "/auth/reset-password";
        }
    }
}
