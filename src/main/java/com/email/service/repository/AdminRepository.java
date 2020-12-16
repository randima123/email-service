package com.email.service.repository;

import com.email.service.dto.Admin;
import com.email.service.dto.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Admin findByIsActive(Boolean active);

}
