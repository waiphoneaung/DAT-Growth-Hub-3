package com.g3.elis.dto.form;

import com.g3.elis.model.CourseAssignment;
import com.g3.elis.model.EnrolledModule;

public class EnrolledAssignmentDto {

	private int totalScore;
	private boolean completeStatus;
	private EnrolledModule enrolledModule;
	private CourseAssignment courseAssignment;

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

	public EnrolledAssignmentDto(int totalScore, boolean completeStatus, EnrolledModule enrolledModule,
			CourseAssignment courseAssignment) {
		super();
		this.totalScore = totalScore;
		this.completeStatus = completeStatus;
		this.enrolledModule = enrolledModule;
		this.courseAssignment = courseAssignment;
	}

	public EnrolledAssignmentDto() {

	}
}
