package com.g3.elis.dto.report;

import com.g3.elis.model.User;

public class QuizPerformance {
	private String courseTitle;
	private String assignmentTitle;
	private String studentName;
	private String studentProfileImage;
	private User student;
	private double averageScore;
	private double passRate;
	private double highestScore;
	private double lowestScore;
	private int timesTakenQuiz;
	private String grade;

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public String getAssignmentTitle() {
		return assignmentTitle;
	}

	public void setAssignmentTitle(String assignmentTitle) {
		this.assignmentTitle = assignmentTitle;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentProfileImage() {
		return studentProfileImage;
	}

	public void setStudentProfileImage(String studentProfileImage) {
		this.studentProfileImage = studentProfileImage;
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public double getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(double averageScore) {
		this.averageScore = averageScore;
	}

	public double getPassRate() {
		return passRate;
	}

	public void setPassRate(double passRate) {
		this.passRate = passRate;
	}

	public double getHighestScore() {
		return highestScore;
	}

	public void setHighestScore(double highestScore) {
		this.highestScore = highestScore;
	}

	public double getLowestScore() {
		return lowestScore;
	}

	public void setLowestScore(double lowestScore) {
		this.lowestScore = lowestScore;
	}

	public int getTimesTakenQuiz() {
		return timesTakenQuiz;
	}

	public void setTimesTakenQuiz(int timesTakenQuiz) {
		this.timesTakenQuiz = timesTakenQuiz;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

}
