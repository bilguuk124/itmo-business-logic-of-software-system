package com.roclh.blps.scheduler;

import com.roclh.blps.entities.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ApproveJobService {

    @Autowired
    private JavaMailSender emailSender;
    public void executeApproveJob(List<Account> admins) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@blps.com");
        message.setSubject("Approve new articles");
        message.setText("There are numerous new articles! Approve them!");
        for (Account admin : admins){
            log.info("Mail is being sent to :" + admin.getUsername());
            message.setTo(admin.getUsername());
            emailSender.send(message);
        }

    }
}
