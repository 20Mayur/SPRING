package com.Springboot.MySql.Entities;

public class Status {
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Status(String status) {
		super();
		this.status = status;
	}

	public Status() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Status [status=" + status + "]";
	}
   
}
