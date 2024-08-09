package com.g3.elis.dto.form;

import java.sql.Timestamp;

import jakarta.validation.constraints.NotNull;

public class ForumCommentDto {
	
	@NotNull(message = "Message cannot be null!")
	private String comment;
		
	private Timestamp createdAt;
	private Timestamp updatedAt;
	
//	@NotNull(message = "Forum is mandatory")
//    private ForumDTO forum;
//
//    @NotNull(message = "User is mandatory")
//    private UserDTO user;

	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
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
	public ForumCommentDto(@NotNull(message = "Message cannot be null!") String comment, Timestamp createdAt,
			Timestamp updatedAt) {
		super();
		this.comment = comment;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public ForumCommentDto() {
		super();
	}
	
}
