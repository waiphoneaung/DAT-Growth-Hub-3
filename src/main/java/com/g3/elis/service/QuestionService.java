package com.g3.elis.service;

import com.g3.elis.dto.form.QuestionCreationSuperDto;
import com.g3.elis.dto.form.QuestionDto;
import com.g3.elis.model.Question;

public interface QuestionService {
	void createQuestion(QuestionDto questionDto,int courseAssignmentId);
	void createQuestionAndAnswer(QuestionCreationSuperDto superDto,int courseAssignmentId);
	void editQuestion(QuestionDto questionDto,int courseAssignmentId,int questionId);
	Question getQuestionById(int questionId);
	void deleteQuestion(int questionId);
}
