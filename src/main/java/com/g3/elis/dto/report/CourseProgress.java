package com.g3.elis.dto.report;

import java.sql.Timestamp;

public class CourseProgress {
	private String courseTitle;
	private String courseImage;
	private int courseId;
	private String staffId;
	private String enrolledUserName;
	private String enrolledUserProfileImage;
	private Timestamp enrolledDate;
	private int enrolledUserId;
	private double progress;
	private double averageScore;
	private String grade;

	public CourseProgress() {

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

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getEnrolledUserName() {
		return enrolledUserName;
	}

	public void setEnrolledUserName(String enrolledUserName) {
		this.enrolledUserName = enrolledUserName;
	}

	public String getEnrolledUserProfileImage() {
		return enrolledUserProfileImage;
	}

	public void setEnrolledUserProfileImage(String enrolledUserProfileImage) {
		this.enrolledUserProfileImage = enrolledUserProfileImage;
	}

	public Timestamp getEnrolledDate() {
		return enrolledDate;
	}

	public void setEnrolledDate(Timestamp enrolledDate) {
		this.enrolledDate = enrolledDate;
	}

	public int getEnrolledUserId() {
		return enrolledUserId;
	}

	public void setEnrolledUserId(int enrolledUserId) {
		this.enrolledUserId = enrolledUserId;
	}

	public double getProgress() {
		return progress;
	}

	public void setProgress(double progress) {
		this.progress = progress;
	}

	public double getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(double averageScore) {
		this.averageScore = averageScore;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

}
