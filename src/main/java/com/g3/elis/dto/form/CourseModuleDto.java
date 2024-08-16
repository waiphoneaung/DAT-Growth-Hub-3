package com.g3.elis.dto.form;

import java.sql.Timestamp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CourseModuleDto {

	private String moduleTitle;
	private String duration;
	private int maxScore;
	private int totalResultScore;
	
	private Timestamp createdAt;
	
	
	private Timestamp updatedAt;
	
	private int index;

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

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public CourseModuleDto(String moduleTitle, String duration, int maxScore, int totalResultScore, Timestamp createdAt,
			Timestamp updatedAt, int index) {
		super();
		this.moduleTitle = moduleTitle;
		this.duration = duration;
		this.maxScore = maxScore;
		this.totalResultScore = totalResultScore;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.index = index;
	}

	public CourseModuleDto()
	{
		
	}

	@Override
	public String toString() {
		return "CourseModuleDto [moduleTitle=" + moduleTitle + ", duration=" + duration + ", maxScore=" + maxScore
				+ ", totalResultScore=" + totalResultScore + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", index=" + index + "]";
	}
	
}
