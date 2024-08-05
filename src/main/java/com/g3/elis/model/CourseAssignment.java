package com.g3.elis.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="course_assignments")
public class CourseAssignment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "title", nullable = false, length = 50)
	private String title;
	
	@Column(name = "finished_status")
	private boolean finishedStatus;
	
	@Column(name = "time_limit")
	private int timeLimit;
	
	@Column(name = "score")
	private int score;
	
	@ManyToOne
	@JoinColumn(name = "course_module_id", nullable = false)
	private CourseModule courseModules;
	
	@OneToMany(mappedBy = "courseAssignments", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<Question> questions;

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

	public boolean isFinishedStatus() {
		return finishedStatus;
	}

	public void setFinishedStatus(boolean finishedStatus) {
		this.finishedStatus = finishedStatus;
	}

	public int getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public CourseModule getCourseModule() {
		return courseModules;
	}

	public void setCourseModule(CourseModule courseModule) {
		this.courseModules = courseModule;
	}

	public List<Question> getQuestion() {
		return questions;
	}

	public void setQuestion(List<Question> question) {
		this.questions = question;
	}

	public CourseAssignment(int id, String title, boolean finishedStatus, int timeLimit, int score,
			CourseModule courseModule, List<Question> question) {
		super();
		this.id = id;
		this.title = title;
		this.finishedStatus = finishedStatus;
		this.timeLimit = timeLimit;
		this.score = score;
		this.courseModules = courseModule;
		this.questions = question;
	}

	public CourseAssignment() {
		super();
	}

	
}
