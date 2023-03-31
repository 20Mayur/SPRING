package com.Springboot.MySql.Request;

import java.util.List;

public class EmailRequest {
	 private String id;
	    private List<String> emailAddresses;
	    private String subject;
	    private String message;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public List<String> getEmailAddresses() {
			return emailAddresses;
		}
		public void setEmailAddresses(List<String> emailAddresses) {
			this.emailAddresses = emailAddresses;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}

	 
}


