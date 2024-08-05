package com.g3.elis.dto.form;

import java.sql.Timestamp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public class InputFileDto {

	@NotBlank(message = "File name cannot be blank")
    private String fileName;

    @NotNull(message = "Creation date cannot be null")
    @PastOrPresent(message = "Creation date must be in the past or present")
    private Timestamp createdAt;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public InputFileDto(@NotBlank(message = "File name cannot be blank") String fileName,
			@NotNull(message = "Creation date cannot be null") @PastOrPresent(message = "Creation date must be in the past or present") Timestamp createdAt) {
		super();
		this.fileName = fileName;
		this.createdAt = createdAt;
	}

	public InputFileDto() {
		super();
	}

   
}
