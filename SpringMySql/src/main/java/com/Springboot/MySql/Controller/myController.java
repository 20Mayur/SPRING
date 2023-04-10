package com.Springboot.MySql.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/mail")
public class myController {
	
	
	 @Autowired
	    private JavaMailSender javaMailSender;

	    @PostMapping("/SendMail")
	    public ResponseEntity<String> sendEmail(
	            @RequestParam("to") String to,
	            @RequestParam("subject") String subject,
	            @RequestParam("message") String message) {

	        SimpleMailMessage mailMessage = new SimpleMailMessage();
	        mailMessage.setTo(to);
	        mailMessage.setSubject(subject);
	        mailMessage.setText(message);

	        try {
	            javaMailSender.send(mailMessage);
	            return ResponseEntity.ok("Email sent successfully!");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body("Failed to send email: " + e.getMessage());
	        }
	    }
}
