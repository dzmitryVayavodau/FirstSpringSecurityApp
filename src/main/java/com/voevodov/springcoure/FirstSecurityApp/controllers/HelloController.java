package com.voevodov.springcoure.FirstSecurityApp.controllers;

import com.voevodov.springcoure.FirstSecurityApp.security.PersonDetails;
import com.voevodov.springcoure.FirstSecurityApp.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloController {

    private final AdminService adminService;

    @Autowired
    public HelloController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    @GetMapping("/showUserInfo")
    @ResponseBody
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        System.out.println(personDetails.getPerson());

        return personDetails.toString();
    }

    @GetMapping("/admin")
    public String adminPage(){
        System.out.println("We are inside HelloController, adminPage");
        adminService.doAdminStuff();
        return "admin";
    }

}
