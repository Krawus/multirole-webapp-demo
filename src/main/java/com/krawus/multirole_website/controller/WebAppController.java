package com.krawus.multirole_website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebAppController {
    @GetMapping("/")
    public String showHomePage(){
        return "home";
    }

    @GetMapping("/management")
    public @ResponseBody String showManagementPage(){
        return "management-page";
    }

    @GetMapping("/management/mod")
    public @ResponseBody String showModPage(){
        return "mod-page";
    }

    @GetMapping("/management/admin")
    public @ResponseBody String showAdminPage(){
        return "admin-page";
    }
    
}
