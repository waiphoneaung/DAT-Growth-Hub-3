package com.g3.elis.dto.form;

import java.util.List;

public class QuizDto {
	private int questionId;
	private List<List<AnswerDto>> answerDtoList;

	public QuizDto() {

	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public List<List<AnswerDto>> getAnswerDtoList() {
		return answerDtoList;
	}

	public void setAnswerDtoList(List<List<AnswerDto>> answerDtoList) {
		this.answerDtoList = answerDtoList;
	}

	public QuizDto(int questionId, List<List<AnswerDto>> answerDtoList) {
		super();
		this.questionId = questionId;
		this.answerDtoList = answerDtoList;
	}
}
