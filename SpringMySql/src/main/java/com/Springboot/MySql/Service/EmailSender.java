package com.Springboot.MySql.Service;

import java.util.concurrent.Callable;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.Springboot.MySql.Dao.EmailRepository;
import com.Springboot.MySql.Entities.Notification;
import com.Springboot.MySql.Entities.Status;

import jakarta.mail.internet.MimeMessage;


public class EmailSender implements Callable<Boolean>{
	@Autowired
	private Notification notication;
	
	@Autowired
	private Status status;
	
	@Autowired
	private EmailRepository emailRepository;
	
	 private JavaMailSender javaMailSender;
	    private String id;
	    private List<String> emailAddresses;
	    private String subject;
	    private String message;
	   
		

	    public EmailSender(String id,JavaMailSender javaMailSender, List<String> emailAddresses, String subject, String message, EmailRepository emailRepository) {
	    	this.id=id;
	        this.javaMailSender = javaMailSender;
	        this.emailAddresses = emailAddresses;
	        this.subject = subject;
	        this.message = message;
	        this.emailRepository=emailRepository;
	        
	    }
	   
	 

	    @Override
	    public Boolean call() throws Exception {
	        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	        Logger logger=LoggerFactory.getLogger(EmailThreadService.class);
	        for (String email : emailAddresses) {
	        	Notification notification=new Notification();
	        	notification.setTo(email);
	        	logger.info("Inside method");
	            SimpleMailMessage mailMessage = new SimpleMailMessage();
	            logger.info(email);
	            mailMessage.setTo(email);
	            logger.info(subject);
	            mailMessage.setFrom("mkurade765@gmail.com");
	            notification.setFrom("mkurade765@gmail.com");
	            mailMessage.setSubject(subject);
	            logger.info(message);
	            mailMessage.setText(message);
	            boolean isSent = false;
	            try {
	                javaMailSender.send(mailMessage);
	                isSent = true;
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	            Status status=new Status();
	           
	            if(isSent) {
	           status.setStatus("Success");
	           notification.setStatus(Arrays.asList(status));
	            }
	            else {  
	            status.setStatus("Failed");
	            notification.setStatus(Arrays.asList(status));
	            }
	              emailRepository.insert(notification);
	        
	        }
			return true;
	          
	        }
	    public FutureTask<Boolean> sendEmailAsync() {
	        FutureTask<Boolean> futureTask = new FutureTask<>(this);

	       
	        ExecutorService executorService = Executors.newFixedThreadPool(10);
	        executorService.submit(futureTask);
	        return futureTask;
	    }
}


