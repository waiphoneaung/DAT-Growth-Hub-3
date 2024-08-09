package com.g3.elis.dto.form;

import java.sql.Timestamp;

public class InputFileDto {

	private String fileName;

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

	public InputFileDto(String fileName, Timestamp createdAt) {
		super();
		this.fileName = fileName;
		this.createdAt = createdAt;

	}

	public InputFileDto() {
		super();
	}

}
