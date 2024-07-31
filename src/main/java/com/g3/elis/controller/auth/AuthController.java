package com.g3.elis.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public String loginPage() {
        return "sign-in";
    }

    @GetMapping("/accessDenied")
    public String accessDeniedPage() {
        return "error404";
    }

}