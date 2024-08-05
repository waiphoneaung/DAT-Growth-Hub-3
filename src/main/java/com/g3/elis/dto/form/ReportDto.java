package com.g3.elis.dto.form;

import java.sql.Timestamp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ReportDto {
	
	@NotBlank(message = "Report type is required")
    @Size(max = 255, message = "Report type must be less than 255 characters")
    private String reportType;

    
    private Timestamp createdAt;

    @NotNull(message = "User ID is required")
    private int user_id;

    @NotNull(message = "Course ID is required")
    private int course_id;

    @NotNull(message = "Enrolled course ID is required")
    private int enrolled_course_id;

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
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

	public int getEnrolled_course_id() {
		return enrolled_course_id;
	}

	public void setEnrolled_course_id(int enrolled_course_id) {
		this.enrolled_course_id = enrolled_course_id;
	}

	public ReportDto(
			@NotBlank(message = "Report type is required") @Size(max = 255, message = "Report type must be less than 255 characters") String reportType,
			Timestamp createdAt, @NotNull(message = "User ID is required") int user_id,
			@NotNull(message = "Course ID is required") int course_id,
			@NotNull(message = "Enrolled course ID is required") int enrolled_course_id) {
		super();
		this.reportType = reportType;
		this.createdAt = createdAt;
		this.user_id = user_id;
		this.course_id = course_id;
		this.enrolled_course_id = enrolled_course_id;
	}

    
}
