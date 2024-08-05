package com.g3.elis.dto.form;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CourseMaterialDto {

    @NotNull(message = "Status cannot be null")

	private boolean status;

	@Size(max = 50, message = "Title cannot exceed 50 characters")
    private String title;
	
	@Size(max = 255, message = "Content cannot exceed 255 characters")
    private String content;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public CourseMaterialDto(@NotNull(message = "Status cannot be null") boolean status,
			@Size(max = 50, message = "Title cannot exceed 50 characters") String title,
			@Size(max = 255, message = "Content cannot exceed 255 characters") String content) {
		super();
		this.status = status;
		this.title = title;
		this.content = content;
	}

	public CourseMaterialDto() {
		super();
	}

	
	
}
