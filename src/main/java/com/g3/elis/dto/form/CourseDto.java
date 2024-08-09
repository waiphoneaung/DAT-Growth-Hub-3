package com.g3.elis.dto.form;

import jakarta.validation.constraints.*;

import java.sql.Timestamp;


public class CourseDto {

    

//    @NotBlank(message = "Course title is required")
//    @Size(max = 255, message = "Course title must be less than 255 characters")
    private String courseTitle;

//    @NotBlank(message = "Course description is required")
//    @Size(max = 500, message = "Course description must be less than 500 characters")
    private String courseDescription;

//    @NotBlank(message = "Status is required")
//    @Size(max = 50, message = "Status must be less than 50 characters")
    private String status;

   
    private Timestamp createdAt;

//    @NotBlank(message = "Duration is required")
//    @Size(max = 50, message = "Duration must be less than 50 characters")
    private String duration;
    
    private int durationHour;
    private int durationMinute;

    private boolean courseCompletedStatus;

   
    private double progress;
    
    private String courseInfo;

    @NotBlank(message = "Course image file name is required")
    
    private String courseImageFileName;

   
    private Timestamp updatedDate;

    @NotNull(message = "User ID is required")
    private int user_id;

    @NotNull(message = "Course category ID is required")
    private int course_category_id;

    @NotNull(message = "Course tag ID is required")
    private int course_tag_id;

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public int getDurationHour() {
		return durationHour;
	}

	public void setDurationHour(int durationHour) {
		this.durationHour = durationHour;
	}

	public int getDurationMinute() {
		return durationMinute;
	}

	public void setDurationMinute(int durationMinute) {
		this.durationMinute = durationMinute;
	}

	public boolean isCourseCompletedStatus() {
		return courseCompletedStatus;
	}

	public void setCourseCompletedStatus(boolean courseCompletedStatus) {
		this.courseCompletedStatus = courseCompletedStatus;
	}

	public double getProgress() {
		return progress;
	}

	public void setProgress(double progress) {
		this.progress = progress;
	}

	public String getCourseInfo() {
		return courseInfo;
	}

	public void setCourseInfo(String courseInfo) {
		this.courseInfo = courseInfo;
	}

	public String getCourseImageFileName() {
		return courseImageFileName;
	}

	public void setCourseImageFileName(String courseImageFileName) {
		this.courseImageFileName = courseImageFileName;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getCourse_category_id() {
		return course_category_id;
	}

	public void setCourse_category_id(int course_category_id) {
		this.course_category_id = course_category_id;
	}

	public int getCourse_tag_id() {
		return course_tag_id;
	}

	public void setCourse_tag_id(int course_tag_id) {
		this.course_tag_id = course_tag_id;
	}

	public CourseDto(String courseTitle, String courseDescription, String status, Timestamp createdAt, String duration,
			int durationHour, int durationMinute, boolean courseCompletedStatus, double progress, String courseInfo,
			@NotBlank(message = "Course image file name is required") String courseImageFileName, Timestamp updatedDate,
			@NotNull(message = "User ID is required") int user_id,
			@NotNull(message = "Course category ID is required") int course_category_id,
			@NotNull(message = "Course tag ID is required") int course_tag_id) {
		super();
		this.courseTitle = courseTitle;
		this.courseDescription = courseDescription;
		this.status = status;
		this.createdAt = createdAt;
		this.duration = duration;
		this.durationHour = durationHour;
		this.durationMinute = durationMinute;
		this.courseCompletedStatus = courseCompletedStatus;
		this.progress = progress;
		this.courseInfo = courseInfo;
		this.courseImageFileName = courseImageFileName;
		this.updatedDate = updatedDate;
		this.user_id = user_id;
		this.course_category_id = course_category_id;
		this.course_tag_id = course_tag_id;
	}

	public CourseDto() {
		
	}
}
