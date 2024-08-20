package com.g3.elis.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g3.elis.dto.form.AnswerDto;
import com.g3.elis.model.Answer;
import com.g3.elis.repository.AnswerRepository;
import com.g3.elis.repository.QuestionRepository;
import com.g3.elis.service.AnswerService;

@Service
public class AnswerServiceImpl implements AnswerService{

	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Override
	public void deleteAnswer(int answerId) 
	{
		answerRepository.deleteById(answerId);
	}

	@Override
	public Answer getAnswerById(int answerId) 
	{
		return answerRepository.findById(answerId).orElse(null);
	}

	@Override
	public void editAnswer(AnswerDto answerDto, int answerId, int questionId) {
		Answer answer = answerRepository.findById(answerId).orElse(null);
		answer.setAnswerTitle(answerDto.getAnswerTitle());
		answer.setCorrectStatus(answerDto.isCorrectStatus());
		answer.setQuestion(questionRepository.findById(questionId).orElse(null));
		answerRepository.save(answer);
	}

	@Override
	public void addAnswer(AnswerDto answerDto, int questionId) {
		Answer answer = new Answer();
		answer.setAnswerTitle(answerDto.getAnswerTitle());
		answer.setCorrectStatus(answerDto.isCorrectStatus());
		answer.setQuestion(questionRepository.findById(questionId).orElse(null));
		answerRepository.save(answer);
	}

}
