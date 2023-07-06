package com.krawus.multirole_website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class LoginFormController {

    @GetMapping("/login")
    public String getLoginForm(){
        return "login-form";
    }
    
}
