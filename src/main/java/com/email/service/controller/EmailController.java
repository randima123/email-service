package com.email.service.controller;

import com.email.service.dto.Email;
import com.email.service.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping("/email")
    public String sendEmail(@RequestBody Email email){
        System.out.println("started");
        emailService.saveEmail(email);
        return "Email Sent";
    }

}
