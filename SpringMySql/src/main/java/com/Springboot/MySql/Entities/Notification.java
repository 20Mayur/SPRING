package com.Springboot.MySql.Entities;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class Notification {
	public Notification(String to, String from, LocalDate date, List<Status> status) {
		super();
		this.to = to;
		this.from = from;
		this.date = date;
		this.status = status;
	}
	private String to;
	private String from;
	private LocalDate date=LocalDate.now();
	private List<Status> status;
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public List<Status> getStatus() {
		return status;
	}
	public void setStatus(List<Status> status) {
		this.status = status;
	}
	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Notification [to=" + to + ", from=" + from + ", date=" + date + "]";
	}
     
}
