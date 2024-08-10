package com.g3.elis.dto.form;

import java.sql.Timestamp;

import com.g3.elis.model.Forum;
import com.g3.elis.model.User;

import jakarta.validation.constraints.NotNull;

public class ForumCommentDto {
	
	@NotNull(message = "Message cannot be null!")
	private String comment;
		
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private User users;
	private int forumId;
	private Forum forums;
	
	
//	@NotNull(message = "Forum is mandatory")
//    private ForumDTO forum;
//
//    @NotNull(message = "User is mandatory")
//    private UserDTO user;

	public Forum getForums() {
		return forums;
	}
	public void setForums(Forum forums) {
		this.forums = forums;
	}
	public User getUsers() {
		return users;
	}
	public void setUsers(User users) {
		this.users = users;
	}
	
	public int getForumId() {
		return forumId;
	}
	public void setForumId(int forumId) {
		this.forumId = forumId;
	}
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
			Timestamp updatedAt, User users, int forumId) {
		super();
		this.comment = comment;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.users = users;
		this.forumId = forumId;
	}
	
	
	public ForumCommentDto(@NotNull(message = "Message cannot be null!") String comment, Timestamp createdAt,
			Timestamp updatedAt, User users, int forumId, Forum forums) {
		super();
		this.comment = comment;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.users = users;
		this.forumId = forumId;
		this.forums = forums;
	}
	public ForumCommentDto() {
		super();
	}
	
}
