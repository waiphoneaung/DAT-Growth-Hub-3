package com.g3.elis.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g3.elis.dto.form.CourseAssignmentDto;
import com.g3.elis.model.Answer;
import com.g3.elis.model.CourseAssignment;
import com.g3.elis.model.Question;
import com.g3.elis.repository.AnswerRepository;
import com.g3.elis.repository.CourseAssignmentReopsitory;
import com.g3.elis.repository.QuestionRepository;
import com.g3.elis.service.CourseAssignmentService;
import com.g3.elis.service.CourseModuleService;

@Service
public class CourseAssignmentServiceImpl implements CourseAssignmentService{

	@Autowired
	private CourseAssignmentReopsitory courseAssignmentRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private CourseModuleService courseModuleService;	
	
	@Override
	public void createAssignment(CourseAssignmentDto courseAssignmentDto, int courseId, int courseModuleId) {
		CourseAssignment courseAssignment = new CourseAssignment();
		courseAssignment.setTitle(courseAssignmentDto.getTitle());
		courseAssignment.setCourseModule(courseModuleService.getCourseModuleById(courseModuleId));
		courseAssignmentRepository.save(courseAssignment);
	}

	@Override
	public CourseAssignment getCourseAssignmentById(int courseAssignmentId) {
		return courseAssignmentRepository.findById(courseAssignmentId).orElse(null);
	}

	@Override
	public void editAssignment(CourseAssignmentDto courseAssignmentDto, int courseModuleId,int courseAssignmentId) 
	{
		CourseAssignment courseAssignment = courseAssignmentRepository.findById(courseAssignmentId).orElse(null);
		courseAssignment.setTitle(courseAssignmentDto.getTitle());
		courseAssignment.setCourseModule(courseModuleService.getCourseModuleById(courseModuleId));
		courseAssignmentRepository.save(courseAssignment);
	}

	@Override
	public void deleteAssignment(int courseModuleId, int courseAssignmentId) 
	{
		CourseAssignment courseAssignment = courseAssignmentRepository.findById(courseAssignmentId).orElse(null);
//		for(Question question : courseAssignment.getQuestions())
//		{
//			for(Answer answer : question.getAnswers())
//			{
//				answer.setQuestion(null);
//				
//			}
//			question.setCourseAssignments(null);
//		}
//		courseAssignment.setCourseModule(null);
		
		courseAssignmentRepository.delete(courseAssignment);
	}

}
