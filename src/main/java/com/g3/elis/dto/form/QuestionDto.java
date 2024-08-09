package com.g3.elis.dto.form;

import jakarta.validation.constraints.NotBlank;
public class QuestionDto {

//	@NotBlank(message = "Question cannot be blank")
	private String question;
	
	
	private boolean correctStatus;


	public String getQuestion() {
		return question;
	}


	public void setQuestion(String question) {
		this.question = question;
	}


	public boolean isCorrectStatus() {
		return correctStatus;
	}


	public void setCorrectStatus(boolean correctStatus) {
		this.correctStatus = correctStatus;
	}


	public QuestionDto(@NotBlank(message = "Question cannot be blank") String question, boolean correctStatus) {
		super();
		this.question = question;
		this.correctStatus = correctStatus;
	}


	public QuestionDto() {
		super();
	}
	
	

}
