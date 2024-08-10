package com.g3.elis.dto.form;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.g3.elis.model.User;

import jakarta.validation.constraints.NotBlank;

public class ForumDto {
	
	@NotBlank(message = "Title cannot be null!")
	private String title;
	
	@NotBlank(message = "Content cannot be null")
	private String content;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private User users;
	//private List<ForumCommentDTO> forumCommentDto = new ArrayList<>();
	private List<ForumCommentDto> forumCommentDto;
	
    public User getUsers() {
		return users;
	}
	public void setUsers(User users) {
		this.users = users;
	}
	

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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

	public ForumDto(@NotBlank(message = "Title cannot be null!") String title,
			@NotBlank(message = "Content cannot be null") String content, Timestamp createdAt, Timestamp updatedAt,
			 User users) {
		super();
		this.title = title;
		this.content = content;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.users = users;
	}
	public ForumDto() {
		super();
	}
	
	
}
