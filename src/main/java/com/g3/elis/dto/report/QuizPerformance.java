package com.g3.elis.dto.report;

public class QuizPerformance {
	private String assignmentTitle;
	private String studentName;
	private String studentProfileImage;
	private double averageScore;
	private double passRate;
	private double highestScore;
	private double lowestScore;

	public QuizPerformance() {

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

}
