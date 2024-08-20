package com.g3.elis.dto.form;

import java.util.List;

public class QuestionCreationSuperDto {
	private QuestionDto questionDto;
	private List<AnswerDto> answerDtoList;
	public QuestionDto getQuestionDto() {
		return questionDto;
	}
	public void setQuestionDto(QuestionDto questionDto) {
		this.questionDto = questionDto;
	}
	public List<AnswerDto> getAnswerDtoList() {
		return answerDtoList;
	}
	public void setAnswerDtoList(List<AnswerDto> answerDtoList) {
		this.answerDtoList = answerDtoList;
	}
	public QuestionCreationSuperDto(QuestionDto questionDto, List<AnswerDto> answerDtoList) {
		super();
		this.questionDto = questionDto;
		this.answerDtoList = answerDtoList;
	}
	
	public QuestionCreationSuperDto()
	{
		
	}
}
