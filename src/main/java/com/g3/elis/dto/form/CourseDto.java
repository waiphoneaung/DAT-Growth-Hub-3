package com.g3.elis.dto.form;

import java.sql.Timestamp;

public class CourseDto {

    private String courseTitle;
    private String courseDescription;
    private String status;
    private Timestamp createdAt;
    private int durationHour;
    private boolean courseCompletedStatus;
    private double progress;
    private String courseInfo;
    private String courseImageFileName;
    private Timestamp updatedDate;
    private int user_id;
    private int course_category_id;
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
	public int getDurationHour() {
		return durationHour;
	}
	public void setDurationHour(int durationHour) {
		this.durationHour = durationHour;
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
	public CourseDto(String courseTitle, String courseDescription, String status, Timestamp createdAt,
			int durationHour, boolean courseCompletedStatus, double progress, String courseInfo,
			String courseImageFileName, Timestamp updatedDate, int user_id, int course_category_id, int course_tag_id) {
		super();
		this.courseTitle = courseTitle;
		this.courseDescription = courseDescription;
		this.status = status;
		this.createdAt = createdAt;
		this.durationHour = durationHour;
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
	@Override
	public String toString() {
		return "CourseDto [courseTitle=" + courseTitle + ", courseDescription=" + courseDescription + ", status="
				+ status + ", createdAt=" + createdAt + ", durationHour=" + durationHour + ", courseCompletedStatus="
				+ courseCompletedStatus + ", progress=" + progress + ", courseInfo=" + courseInfo
				+ ", courseImageFileName=" + courseImageFileName + ", updatedDate=" + updatedDate + ", user_id="
				+ user_id + ", course_category_id=" + course_category_id + ", course_tag_id=" + course_tag_id + "]";
	}
	
}
