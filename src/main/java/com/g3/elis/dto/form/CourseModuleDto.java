package com.g3.elis.dto.form;

import java.sql.Timestamp;
import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public class CourseModuleDto {

	@NotBlank(message = "Module title cannot be blank")
	@Size(max = 255, message = "Module title cannot exceed 255 characters")
	private String moduleTitle;
	
	@NotNull(message = "Duration cannot be null")
	private String duration;
	
	@NotNull(message = "Max score cannot be null")
	private int maxScore;
	
	@NotNull(message = "Toal score cannot be null")
	private int totalResultScore;
	
	private Timestamp createdAt;
	
	
	private Timestamp updatedAt;

	public String getModuleTitle() {
		return moduleTitle;
	}

	public void setModuleTitle(String moduleTitle) {
		this.moduleTitle = moduleTitle;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public int getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}

	public int getTotalResultScore() {
		return totalResultScore;
	}

	public void setTotalResultScore(int totalResultScore) {
		this.totalResultScore = totalResultScore;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public CourseModuleDto(
			@NotBlank(message = "Module title cannot be blank") @Size(max = 255, message = "Module title cannot exceed 255 characters") String moduleTitle,
			@NotNull(message = "Duration cannot be null") String duration,
			@NotNull(message = "Max score cannot be null") int maxScore,
			@NotNull(message = "Toal score cannot be null") int totalResultScore, Timestamp createdAt,
			Timestamp updatedAt) {
		super();
		this.moduleTitle = moduleTitle;
		this.duration = duration;
		this.maxScore = maxScore;
		this.totalResultScore = totalResultScore;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public CourseModuleDto() {
		super();
	}

		
}
