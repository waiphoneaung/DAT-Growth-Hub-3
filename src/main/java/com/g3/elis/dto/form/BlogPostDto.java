package com.g3.elis.dto.form;

import java.sql.Timestamp;

public class BlogPostDto{

	
	
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private String title;
	private String description;
	private String content;
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public BlogPostDto(Timestamp createdAt, Timestamp updatedAt, String title, String description, String content) {
		super();
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.title = title;
		this.description = description;
		this.content = content;
	}
	public BlogPostDto() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	
}