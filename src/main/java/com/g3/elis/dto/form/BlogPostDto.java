package com.g3.elis.dto.form;

import java.sql.Timestamp;

import com.g3.elis.model.User;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class BlogPostDto {
	private int id;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private String imageFile;
	private String htmlFileName;

	@NotEmpty(message = "Title is required")
	@Size(min = 3, message = "Title must be at least 5 characters long")
	private String title;

	private User users;

	public BlogPostDto(int id, Timestamp createdAt, Timestamp updatedAt, String imageFile, String htmlFileName,
			@NotEmpty(message = "Title is required") @Size(min = 3, message = "Title must be at least 5 characters long") String title,
			User users) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.imageFile = imageFile;
		this.htmlFileName = htmlFileName;
		this.title = title;
		this.users = users;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getImageFile() {
		return imageFile;
	}

	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}

	public String getHtmlFileName() {
		return htmlFileName;
	}

	public void setHtmlFileName(String htmlFileName) {
		this.htmlFileName = htmlFileName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public BlogPostDto() {

	}
}