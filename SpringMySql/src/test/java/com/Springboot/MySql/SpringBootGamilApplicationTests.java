package com.Springboot.MySql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.Springboot.MySql.Controller.myController;

@SpringBootTest
@AutoConfigureMockMvc
class SpringBootGamilApplicationTests {


	  @Mock
	    private JavaMailSender javaMailSender;

	    @InjectMocks
	    private myController MyController;

	    @Test
	    void sendEmail_shouldSendEmailSuccessfully() {
	        // given
	        String to = "recipient@example.com";
	        String subject = "Test email";
	        String message = "This is a test email";
	        SimpleMailMessage mailMessage = new SimpleMailMessage();
	        mailMessage.setTo(to);
	        mailMessage.setSubject(subject);
	        mailMessage.setText(message);

	        // when
	        ResponseEntity<String> response = MyController.sendEmail(to, subject, message);

	        // then
	        verify(javaMailSender).send(mailMessage);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals("Email sent successfully!", response.getBody());
	    }

	    @Test
	    void sendEmail_shouldHandleException() {
	        // given
	        String to = "recipient@example.com";
	        String subject = "Test email";
	        String message = "This is a test email";
	        SimpleMailMessage mailMessage = new SimpleMailMessage();
	        mailMessage.setTo(to);
	        mailMessage.setSubject(subject);
	        mailMessage.setText(message);
	        doThrow(new RuntimeException("Failed to send email")).when(javaMailSender).send(any(SimpleMailMessage.class));

	        // when
	        ResponseEntity<String> response = MyController.sendEmail(to, subject, message);

	        // then
	        verify(javaMailSender).send(mailMessage);
	        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	        assertEquals("Failed to send email: Failed to send email", response.getBody());
	    }
	    
	  
}
