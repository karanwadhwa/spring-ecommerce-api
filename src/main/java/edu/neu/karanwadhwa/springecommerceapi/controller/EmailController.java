package edu.neu.karanwadhwa.springecommerceapi.controller;

import edu.neu.karanwadhwa.springecommerceapi.model.EmailDetails;
import edu.neu.karanwadhwa.springecommerceapi.service.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/sendemail")
    public String sendEmail(@RequestBody EmailDetails details){
        String status = emailService.sendSimpleEmail(details);
        return status;
    }
}
