package com.email.service.service;

import com.email.service.dto.Email;
import com.email.service.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    EmailService emailService;

    @Scheduled(fixedRate = 2000)
    public void scheduleTaskWithFixedRate() {

        Email email = emailRepository.findFirstByStatus("Pending");

        if(email != null){
            String response = emailService.sendEmail(email);
            System.out.println(response);
        }else{
            System.out.println("No pending emails");
        }

    }
}
