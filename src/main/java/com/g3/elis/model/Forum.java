package com.g3.elis.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="forums")
public class Forum {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String title;
	private String content;
	private Timestamp created_at;
	private Timestamp updated_at;
	
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
	

	public Forum(int id, String title, String content, Timestamp created_at, Timestamp updated_at,
			List<ForumComment> forumComments, User users) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.forumComments = forumComments;
		this.users = users;
	}

	public Forum() {
		super();
	}
	

}
