package com.g3.elis.dto.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AnswerDto {

//	@NotBlank(message = "Answer title cannot be blank")
//    @Size(max = 255, message = "Answer title cannot exceed 255 characters")
	private String answerTitle;
	private boolean correctStatus;
	public String getAnswerTitle() {
		return answerTitle;
	}
	public void setAnswerTitle(String answerTitle) {
		this.answerTitle = answerTitle;
	}
	public boolean isCorrectStatus() {
		return correctStatus;
	}
	public void setCorrectStatus(boolean correctStatus) {
		this.correctStatus = correctStatus;
	}
	public AnswerDto(
			@NotBlank(message = "Answer title cannot be blank") @Size(max = 255, message = "Answer title cannot exceed 255 characters") String answerTitle,
			boolean correctStatus) {
		super();
		this.answerTitle = answerTitle;
		this.correctStatus = correctStatus;
	}
	public AnswerDto() {
		super();
	}
	

}
