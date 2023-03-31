package com.Springboot.MySql.Service;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.Springboot.MySql.Dao.EmailRepository;
@Component
public class EmailThreadService {
	@Autowired
    private JavaMailSender javaMailSender;
	
	@Autowired
	private EmailRepository emailRepository;
   
    public void sendEmailToMultipleAddresses(String id,List<String> emailAddresses, String subject, String message) throws ExecutionException, InterruptedException {
        EmailSender emailSender = new EmailSender(id, javaMailSender, emailAddresses, subject, message, emailRepository);

        emailSender.sendEmailAsync();
        
    }
}
