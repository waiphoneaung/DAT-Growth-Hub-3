package com.g3.elis.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g3.elis.dto.form.QuestionDto;
import com.g3.elis.model.Question;
import com.g3.elis.repository.CourseAssignmentReopsitory;
import com.g3.elis.repository.QuestionRepository;
import com.g3.elis.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService
{

	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private CourseAssignmentReopsitory courseAssignmentReopsitory;
	
	@Override
	public void createQuestion(QuestionDto questionDto, int courseAssignmentId) {
		Question question = new Question();
		question.setQuestion(questionDto.getQuestion());
		question.setCorrectStatus(questionDto.isCorrectStatus());
		question.setCourseAssignments(courseAssignmentReopsitory.findById(courseAssignmentId).orElse(null));
		questionRepository.save(question);
	}

	@Override
	public void editQuestion(QuestionDto questionDto, int courseAssignmentId, int questionId) {
		Question question = questionRepository.findById(courseAssignmentId).orElse(null);
		question.setQuestion(questionDto.getQuestion());
		question.setCorrectStatus(questionDto.isCorrectStatus());
		question.setCourseAssignments(courseAssignmentReopsitory.findById(courseAssignmentId).orElse(null));
		questionRepository.save(question);
	}

	@Override
	public Question getQuestionById(int questionId) {
		return questionRepository.findById(questionId).orElse(null);
	}
	
}
