package com.g3.elis.model;

import java.util.ArrayList;
import java.util.List;

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
@Table(name = "enrolled_assignments")
public class EnrolledAssignment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int totalScore;
	private boolean completeStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "enrolled_module_id")
	private EnrolledModule enrolledModule;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_assignment_id")
	private CourseAssignment courseAssignment;

	@OneToMany(mappedBy = "enrolledAssignment", fetch = FetchType.LAZY)
	private List<Grade> grades = new ArrayList<Grade>();

	public EnrolledAssignment() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public boolean isCompleteStatus() {
		return completeStatus;
	}

	public void setCompleteStatus(boolean completeStatus) {
		this.completeStatus = completeStatus;
	}

	public EnrolledModule getEnrolledModule() {
		return enrolledModule;
	}

	public void setEnrolledModule(EnrolledModule enrolledModule) {
		this.enrolledModule = enrolledModule;
	}

	public CourseAssignment getCourseAssignment() {
		return courseAssignment;
	}

	public void setCourseAssignment(CourseAssignment courseAssignment) {
		this.courseAssignment = courseAssignment;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	public EnrolledAssignment(int id, int totalScore, boolean completeStatus, EnrolledModule enrolledModule,
			CourseAssignment courseAssignment, List<Grade> grades) {
		super();
		this.id = id;
		this.totalScore = totalScore;
		this.completeStatus = completeStatus;
		this.enrolledModule = enrolledModule;
		this.courseAssignment = courseAssignment;
		this.grades = grades;
	}

}
