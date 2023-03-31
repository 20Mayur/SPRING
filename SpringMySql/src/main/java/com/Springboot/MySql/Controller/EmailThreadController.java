package com.Springboot.MySql.Controller;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Springboot.MySql.Request.EmailRequest;
import com.Springboot.MySql.Service.EmailThreadService;
@RestController
public class EmailThreadController {

		    @Autowired
		    private EmailThreadService emailService;
		 

		    @PostMapping("/send-email")
		    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) {
		        try {
		            emailService.sendEmailToMultipleAddresses(emailRequest.getId(), emailRequest.getEmailAddresses(), emailRequest.getSubject(), emailRequest.getMessage());
		            return new ResponseEntity<>("Email sent successfully", HttpStatus.OK);
		        } catch (ExecutionException | InterruptedException e) {
		            e.printStackTrace();
		            return new ResponseEntity<>("Failed to send email", HttpStatus.INTERNAL_SERVER_ERROR);
		        }
		    }
		    

	}


