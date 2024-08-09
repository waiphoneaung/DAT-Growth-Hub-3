package com.g3.elis.model;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "forum_comments")
public class ForumComment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String comment;
	private Timestamp created_at;
	private Timestamp updated_at;
	
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

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
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

	public ForumComment(int id, String comment, Timestamp created_at, Timestamp updated_at, Forum forums, User users) {
		super();
		this.id = id;
		this.comment = comment;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.forums = forums;
		this.users = users;
	}

	public ForumComment() {
		super();
	}
	
	
}
