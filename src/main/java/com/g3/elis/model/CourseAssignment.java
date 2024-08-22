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
@Table(name = "course_assignments")
public class CourseAssignment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length=1000)
	private String title;
	private int timeLimit;

	@ManyToOne
	@JoinColumn(name = "course_module_id")
	private CourseModule courseModules;
	
	@OneToMany(mappedBy = "courseAssignment", fetch = FetchType.LAZY)
	private List<EnrolledAssignment> enrolledAssignment;

	@OneToMany(mappedBy = "courseAssignments", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Question> questions;

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

	public int getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}

	public CourseModule getCourseModule() {
		return courseModules;
	}

	public void setCourseModule(CourseModule courseModule) {
		this.courseModules = courseModule;
	}

	public CourseModule getCourseModules() {
		return courseModules;
	}

	public void setCourseModules(CourseModule courseModules) {
		this.courseModules = courseModules;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public CourseAssignment(int id, String title, int timeLimit, CourseModule courseModules, List<Question> questions) {
		super();
		this.id = id;
		this.title = title;
		this.timeLimit = timeLimit;
		this.courseModules = courseModules;
		this.questions = questions;
	}

	public CourseAssignment() {
		super();
	}

}
