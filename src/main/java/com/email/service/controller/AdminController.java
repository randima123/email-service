package com.email.service.controller;

import com.email.service.dto.Admin;
import com.email.service.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/admin")
    public String addAdmin(@RequestBody Admin admin){

        adminService.saveAdmin(admin);
        return "Admin added succesfully";
    }
}
