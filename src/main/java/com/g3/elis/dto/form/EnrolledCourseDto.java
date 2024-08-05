package com.g3.elis.dto.form;

import java.sql.Timestamp;

import jakarta.validation.constraints.NotNull;

public class EnrolledCourseDto {
	
	

   
    private Timestamp enrolledAt;
    private Timestamp completedAt;

    @NotNull(message = "User ID is required")
    private int user_id;

    @NotNull(message = "Course ID is required")
    private int course_id;

	public Timestamp getEnrolledAt() {
		return enrolledAt;
	}

	public void setEnrolledAt(Timestamp enrolledAt) {
		this.enrolledAt = enrolledAt;
	}

	public Timestamp getCompletedAt() {
		return completedAt;
	}

	public void setCompletedAt(Timestamp completedAt) {
		this.completedAt = completedAt;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getCourse_id() {
		return course_id;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}

	public EnrolledCourseDto(Timestamp enrolledAt, Timestamp completedAt,
			@NotNull(message = "User ID is required") int user_id,
			@NotNull(message = "Course ID is required") int course_id) {
		super();
		this.enrolledAt = enrolledAt;
		this.completedAt = completedAt;
		this.user_id = user_id;
		this.course_id = course_id;
	}

    
}
