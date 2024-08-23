package com.g3.elis.dto.report;

import java.sql.Timestamp;

import com.g3.elis.model.User;

public class CoursePerformance {
	private String courseTitle;
	private String courseImage;
	private int courseId;
	private String instructor;
	private Timestamp createdAt;
	private int enrollment;
	private int monthlyEnrolledUser;
	private int yearlyEnrolledUser;
	private double completionRate;
	private double averageScore;

	public CoursePerformance() {

	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public String getCourseImage() {
		return courseImage;
	}

	public void setCourseImage(String courseImage) {
		this.courseImage = courseImage;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public int getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(int enrollment) {
		this.enrollment = enrollment;
	}

	public int getMonthlyEnrolledUser() {
		return monthlyEnrolledUser;
	}

	public void setMonthlyEnrolledUser(int monthlyEnrolledUser) {
		this.monthlyEnrolledUser = monthlyEnrolledUser;
	}

	public int getYearlyEnrolledUser() {
		return yearlyEnrolledUser;
	}

	public void setYearlyEnrolledUser(int yearlyEnrolledUser) {
		this.yearlyEnrolledUser = yearlyEnrolledUser;
	}

	public double getCompletionRate() {
		return completionRate;
	}

	public void setCompletionRate(double completionRate) {
		this.completionRate = completionRate;
	}

	public double getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(double averageScore) {
		this.averageScore = averageScore;
	}
	
}
