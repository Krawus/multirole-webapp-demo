package com.krawus.multirole_website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class LoginFormController {

    @GetMapping("/login")
    public String getLoginForm(){
        return "login-form";
    }

    @GetMapping("/access-denied")
    public @ResponseBody String showAccessDeniedPage(){
        return "access-denied";
    }
    
}
