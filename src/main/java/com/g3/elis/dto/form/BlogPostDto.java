package com.g3.elis.dto.form;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class BlogPostDto{

    private Timestamp createdAt;
    private Timestamp updatedAt;
    
    private MultipartFile imageFile;

	@NotEmpty(message = "Title is required")
    @Size(min = 5, message = "Title must be at least 5 characters long")
    private String title;

    @NotEmpty(message = "Description is required")
    @Size(min = 10, message = "Description must be at least 10 characters long")
    private String description;

    @NotEmpty(message = "Content is required")
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

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
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

	public BlogPostDto(Timestamp createdAt, Timestamp updatedAt, MultipartFile imageFile,
			@NotEmpty(message = "Title is required") @Size(min = 5, message = "Title must be at least 5 characters long") String title,
			@NotEmpty(message = "Description is required") @Size(min = 10, message = "Description must be at least 10 characters long") String description,
			@NotEmpty(message = "Content is required") String content) {
		super();
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.imageFile = imageFile;
		this.title = title;
		this.description = description;
		this.content = content;
	}

	public BlogPostDto() {
		
	}
}