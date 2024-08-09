package com.g3.elis.dto.form;

import jakarta.validation.constraints.NotBlank;

public class CourseAssignmentDto {

//	@NotBlank(message = "Title cannot be blank")
	private String title;
	
	private boolean finishedStatus;

    private int timeLimit;

    private int score;

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

	public CourseAssignmentDto(@NotBlank(message = "Title cannot be blank") String title, boolean finishedStatus,
			int timeLimit, int score) {
		super();
		this.title = title;
		this.finishedStatus = finishedStatus;
		this.timeLimit = timeLimit;
		this.score = score;
	}

	public CourseAssignmentDto() {
		super();
	}
}
