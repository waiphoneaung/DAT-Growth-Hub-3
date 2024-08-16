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
@Table(name="questions")
public class Question {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(columnDefinition = "TEXT")
	private String question;
	private boolean correctStatus;
	
	@ManyToOne
	@JoinColumn(name = "course_assignment_id", nullable = false)
	private CourseAssignment courseAssignments;
	
	@OneToMany(mappedBy = "questions", cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval = true)
	List<Answer> answers;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public boolean isCorrectStatus() {
		return correctStatus;
	}

	public void setCorrectStatus(boolean correctStatus) {
		this.correctStatus = correctStatus;
	}

	public CourseAssignment getCourseAssignments() {
		return courseAssignments;
	}

	public void setCourseAssignments(CourseAssignment courseAssignments) {
		this.courseAssignments = courseAssignments;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public Question(int id, String question, boolean correctStatus, CourseAssignment courseAssignments,
			List<Answer> answers) {
		super();
		this.id = id;
		this.question = question;
		this.correctStatus = correctStatus;
		this.courseAssignments = courseAssignments;
		this.answers = answers;
	}

	public Question() {
		super();
	}

		
}
