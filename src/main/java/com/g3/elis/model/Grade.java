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
@Table(name = "grades")
public class Grade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String grade;
	private int score;
	private Timestamp createdAt;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "enrolled_assignment_id")
	private EnrolledAssignment enrolledAssignment;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public EnrolledAssignment getEnrolledAssignment() {
		return enrolledAssignment;
	}

	public void setEnrolledAssignment(EnrolledAssignment enrolledAssignment) {
		this.enrolledAssignment = enrolledAssignment;
	}

	public Grade(int id, String grade, int score, Timestamp createdAt, User user,
			EnrolledAssignment enrolledAssignment) {
		super();
		this.id = id;
		this.grade = grade;
		this.score = score;
		this.createdAt = createdAt;
		this.user = user;
		this.enrolledAssignment = enrolledAssignment;
	}

	public Grade() {
		
	}
}
