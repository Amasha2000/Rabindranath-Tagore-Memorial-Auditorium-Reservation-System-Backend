package com.ruhuna.reservationsystembackend.services;

import com.ruhuna.reservationsystembackend.enums.ApprovalStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender mailSender;

    //approval status mail
    @Override
    public void sendApprovalStatusEmail(String toEmail, String applicantName, ApprovalStatus status) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(toEmail);
        message.setSubject("Auditorium Reservation Approval Status Updated");
        message.setText("Dear " + applicantName + ",\n\n" +
                "Your reservation's approval status has been updated to: " + status + ".\n" +
                "Thank you for using our service.\n\n" +
                "Best regards,\nUniversity of Ruhuna Auditorium Management");

        mailSender.send(message);
    }
}
