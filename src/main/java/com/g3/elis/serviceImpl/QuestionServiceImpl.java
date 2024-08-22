package com.g3.elis.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g3.elis.dto.form.AnswerDto;
import com.g3.elis.dto.form.QuestionCreationSuperDto;
import com.g3.elis.dto.form.QuestionDto;
import com.g3.elis.model.Question;
import com.g3.elis.model.Answer;
import com.g3.elis.model.EnrolledAssignment;
import com.g3.elis.repository.AnswerRepository;
import com.g3.elis.repository.CourseAssignmentReopsitory;
import com.g3.elis.repository.EnrolledAssignmentRepository;
import com.g3.elis.repository.QuestionRepository;
import com.g3.elis.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService
{

	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private CourseAssignmentReopsitory courseAssignmentReopsitory;
	
	@Autowired
	private EnrolledAssignmentRepository enrolledAssignmentRepository;
	
	@Override
	public void createQuestion(QuestionDto questionDto, int courseAssignmentId) {
		Question question = new Question();
		question.setQuestion(questionDto.getQuestion());
		question.setCorrectStatus(questionDto.isCorrectStatus());
		question.setCourseAssignments(courseAssignmentReopsitory.findById(courseAssignmentId).orElse(null));
		questionRepository.save(question);
	}
	
	@Override
	public void createQuestionAndAnswer(QuestionCreationSuperDto superDto,int courseAssignmentId)
	{
		Question question = new Question();
		List<Answer> answerList= new ArrayList<Answer>();
		question.setQuestion(superDto.getQuestionDto().getQuestion());
		for(AnswerDto answerDto : superDto.getAnswerDtoList())
		{
			Answer answer = new Answer();
			answer.setAnswerTitle(answerDto.getAnswerTitle());
			answer.setCorrectStatus(answerDto.isCorrectStatus());
			answer.setQuestion(question);
			answerList.add(answer);
		}
		question.setAnswers(answerList);
		question.setCourseAssignments(courseAssignmentReopsitory.findById(courseAssignmentId).orElse(null));
		questionRepository.save(question);
	}

	@Override
	public void editQuestion(QuestionDto questionDto, int courseAssignmentId, int questionId) {
		Question question = questionRepository.findById(questionId).orElse(null);
		question.setQuestion(questionDto.getQuestion());
		question.setCorrectStatus(questionDto.isCorrectStatus());
		question.setCourseAssignments(courseAssignmentReopsitory.findById(courseAssignmentId).orElse(null));
		questionRepository.save(question);
	}

	@Override
	public Question getQuestionById(int questionId) {
		return questionRepository.findById(questionId).orElse(null);
	}

	@Override
	public void deleteQuestion(int questionId) {
		Question question = questionRepository.findById(questionId).orElse(null);
		question.setCourseAssignments(null);
		questionRepository.deleteById(questionId);
	}
}
