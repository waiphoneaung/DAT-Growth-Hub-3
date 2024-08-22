package com.g3.elis.dto.form;

import java.util.List;

import com.g3.elis.model.Answer;

public class QuestionDto {

	private String question;
	private boolean correctStatus;
	private List<Answer> answers;
	private List<AnswerDto> answerDto;

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

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public List<AnswerDto> getAnswerDto() {
		return answerDto;
	}

	public void setAnswerDto(List<AnswerDto> answerDto) {
		this.answerDto = answerDto;
	}

	public QuestionDto(String question, boolean correctStatus, List<Answer> answers, List<AnswerDto> answerDto) {
		super();
		this.question = question;
		this.correctStatus = correctStatus;
		this.answers = answers;
		this.answerDto = answerDto;
	}

	public QuestionDto() {

	}
}
