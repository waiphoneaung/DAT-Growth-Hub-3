package com.g3.elis.model;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "achievements")
public class Achievement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String title;
	private String badgeName;
	private String certificateName;
	private Timestamp receivedAt;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User users;

	@OneToOne
	@JoinColumn(name = "course_id")
	private Course courses;

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

	public String getBadgeName() {
		return badgeName;
	}

	public void setBadgeName(String badgeName) {
		this.badgeName = badgeName;
	}

	public String getCertificateName() {
		return certificateName;
	}

	public void setCertificateName(String certificateName) {
		this.certificateName = certificateName;
	}

	public Timestamp getReceivedAt() {
		return receivedAt;
	}

	public void setReceivedAt(Timestamp receivedAt) {
		this.receivedAt = receivedAt;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public Course getCourses() {
		return courses;
	}

	public void setCourses(Course courses) {
		this.courses = courses;
	}

	public Achievement(int id, String title, String badgeName, String certificateName, Timestamp receivedAt, User users,
			Course courses) 
	{
		super();
		this.id = id;
		this.title = title;
		this.badgeName = badgeName;
		this.certificateName = certificateName;
		this.receivedAt = receivedAt;
		this.users = users;
		this.courses = courses;
	}
	
	public Achievement()
	{
		
	}
}
