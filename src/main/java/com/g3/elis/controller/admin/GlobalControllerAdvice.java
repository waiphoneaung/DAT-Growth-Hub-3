package com.g3.elis.controller.admin;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.g3.elis.model.User;
import com.g3.elis.security.LoginUserDetail;



@ControllerAdvice
public class GlobalControllerAdvice {
	
    @ModelAttribute
    public void addUserToModel(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            System.out.println("Principal class: " + principal.getClass());
            if (principal instanceof LoginUserDetail) {
                LoginUserDetail userDetails = (LoginUserDetail) principal;
                User user = userDetails.getUser();
                model.addAttribute("user", user);
            }
        }
    }
}