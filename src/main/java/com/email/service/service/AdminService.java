package com.email.service.service;

import com.email.service.dto.Admin;
import com.email.service.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    public String saveAdmin(Admin admin){
        Admin activeAdmin = adminRepository.findByIsActive(true);
        //if no current active admin, make the new admin active.
        if (activeAdmin == null){
            admin.setActive(true);
            System.out.println("No current active admin. new admin set as active");
        }else{
            admin.setActive(false);
        }
        adminRepository.save(admin);
        System.out.println("Admin created successfully");
        return "Admin created successfully";
    }
}
