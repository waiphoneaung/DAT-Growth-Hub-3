package com.g3.elis.service;

import com.g3.elis.dto.form.AnswerDto;
import com.g3.elis.model.Answer;

public interface AnswerService {
	void addAnswer(AnswerDto answerDto,int questionId);
	void editAnswer(AnswerDto answerDto, int answerId, int questionId);
	void deleteAnswer(int answerId);
	Answer getAnswerById(int answerId);
}
