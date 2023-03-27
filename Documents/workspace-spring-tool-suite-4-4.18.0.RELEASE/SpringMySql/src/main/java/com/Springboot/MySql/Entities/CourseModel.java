package com.Springboot.MySql.Entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CourseModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String title;
	private String description;
	private UUID uuid=UUID.randomUUID();
	private long version=System.currentTimeMillis();
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	public CourseModel(long id, String name, String title, String description, UUID uuid, long version) {
		super();
		this.id = id;
		this.name = name;
		this.title = title;
		this.description = description;
		this.uuid = uuid;
		this.version = version;
	}
	@Override
	public String toString() {
		return "CourseModel [id=" + id + ", name=" + name + ", title=" + title + ", description=" + description
				+ ", uuid=" + uuid + ", version=" + version + "]";
	}
	public CourseModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CourseModel(long id, String name, String title, String description) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.name = name;
		this.title = title;
		this.description = description;
		
	}
	
	
}
