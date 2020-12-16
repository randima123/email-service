package com.email.service.repository;

import com.email.service.dto.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<Email, Integer> {

    Email findFirstByStatus(String status);

}
