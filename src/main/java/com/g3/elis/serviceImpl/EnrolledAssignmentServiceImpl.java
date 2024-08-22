package com.g3.elis.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g3.elis.dto.form.QuizDto;
import com.g3.elis.model.Answer;
import com.g3.elis.model.EnrolledAssignment;
import com.g3.elis.model.Question;
import com.g3.elis.repository.EnrolledAssignmentRepository;
import com.g3.elis.service.EnrolledAssignmentService;

@Service
public class EnrolledAssignmentServiceImpl implements EnrolledAssignmentService{

	@Autowired
	private EnrolledAssignmentRepository enrolledAssignmentRepository;
	
	@Override
	public EnrolledAssignment getEnrolledAssignmentById(int enrolledAssignmentId) {
		return enrolledAssignmentRepository.findById(enrolledAssignmentId).orElse(null);
	}

	@Override
	public boolean submitQuiz(QuizDto quizDto, int enrolledAssignmentId) {
		EnrolledAssignment enrolledAssignment = enrolledAssignmentRepository.findById(enrolledAssignmentId).orElse(null);
		int i = 0;
		for(Question question : enrolledAssignment.getCourseAssignment().getQuestions())
		{
			int j = 0;
			System.out.println("Answer Results");
			for(Answer answer : question.getAnswers())
			{
				if(quizDto.getAnswerDtoList().get(i).get(j).isCorrectStatus() != answer.isCorrectStatus())
				{
					return false;
				}
				j++;
			}
			i++;
		}
		return true;
	}

	@Override
	public void setStatusToTrue(int enrolledAssignmentId) 
	{
		EnrolledAssignment enrolledAssignment = enrolledAssignmentRepository.findById(enrolledAssignmentId).orElse(null);
		enrolledAssignment.setCompleteStatus(true);
		enrolledAssignmentRepository.save(enrolledAssignment);
	}

}
