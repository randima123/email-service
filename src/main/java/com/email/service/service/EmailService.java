package com.email.service.service;

import com.email.service.dto.Admin;
import com.email.service.dto.Email;
import com.email.service.repository.AdminRepository;
import com.email.service.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    AdminRepository adminRepository;

    public JavaMailSender getJavaMailSender() {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        Admin activeAdmin = adminRepository.findByIsActive(true);
        if (activeAdmin == null){
            System.out.println("Error: No active admins.");
            return  null;
        }
        mailSender.setUsername(activeAdmin.getUsername());
        mailSender.setPassword(activeAdmin.getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.connectiontimeout", 5000);
        props.put("mail.smtp.timeout", 5000);
        props.put("mail.smtp.writetimeout", 5000);
        props.put("mail.smtp.starttls.enable", "true");

        return mailSender;
    }

    public String saveEmail(Email email) {

        email.setStatus("Pending");
        emailRepository.save(email);
        return "Email saved";

    }

    public String sendEmail(Email email) {

        JavaMailSender mailSender = getJavaMailSender();
        if(mailSender == null){
            return "Error: Empty mail sender";
        }
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setSubject(email.getTitle());
//            mimeMessageHelper.setFrom("sanayaraizada@gmail.com");
            mimeMessageHelper.setTo(email.getReceiverEmail());
            mimeMessageHelper.setText(email.getMessage());
            mailSender.send(mimeMessageHelper.getMimeMessage());
            email.setStatus("Complete");
            emailRepository.save(email);
            return "Email sent successfully";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Error occurred while sending the email: "+e.getMessage();
        }

    }

}
