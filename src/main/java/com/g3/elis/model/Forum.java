package com.g3.elis.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="forums")
public class Forum {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(length=5000)
	private String title;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	private Timestamp createdAt;
	private Timestamp updatedAt;
	
	@OneToMany(mappedBy ="forums", cascade = CascadeType.ALL)
	private List<ForumComment> forumComments = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User users;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public List<ForumComment> getForumComments() {
		return forumComments;
	}

	public void setForumComments(List<ForumComment> forumComments) {
		this.forumComments = forumComments;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
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

		public Forum() {
		super();
	}

		public Forum(int id, String title, String content, Timestamp createdAt, Timestamp updatedAt,
				List<ForumComment> forumComments, User users) {
			super();
			this.id = id;
			this.title = title;
			this.content = content;
			this.createdAt = createdAt;
			this.updatedAt = updatedAt;
			this.forumComments = forumComments;
			this.users = users;
		}

	
		

}
