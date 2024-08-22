package com.g3.elis.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "forum_comments")
public class ForumComment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(columnDefinition = "TEXT")
	private String comment;
	
	
	@Column(name = "created_at", nullable = false, updatable = false)
	private Timestamp createdAt;

	private Timestamp updatedAt;
	
	@ManyToOne
	@JoinColumn(name = "forum_id")
	private Forum forums;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User users;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

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

	
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	

	public void setCreatedAt(Timestamp createdAt) {
		// TODO Auto-generated method stub
		this.createdAt = createdAt;
	}
	
	public ForumComment() {
		super();
	}

	public ForumComment(int id, String comment, Timestamp createdAt, Timestamp updatedAt, Forum forums, User users) {
		super();
		this.id = id;
		this.comment = comment;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.forums = forums;
		this.users = users;
	}

	
	
}
