package com.g3.elis.serviceImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g3.elis.dto.form.QuizDto;
import com.g3.elis.model.Answer;
import com.g3.elis.model.EnrolledAssignment;
import com.g3.elis.model.Grade;
import com.g3.elis.model.Question;
import com.g3.elis.model.User;
import com.g3.elis.repository.EnrolledAssignmentRepository;
import com.g3.elis.repository.GradeRepository;
import com.g3.elis.repository.UserRepository;
import com.g3.elis.service.GradeService;

@Service
public class GradeServiceImpl implements GradeService{

	@Autowired
	private GradeRepository gradeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EnrolledAssignmentRepository enrolledAssignmentRepository;
	
	
	@Override
	public void createGrade(int userId, int enrolledAssignmentId,QuizDto quizDto) {
		Grade grade = new Grade();
		User user = userRepository.findById(userId).orElse(null);
		EnrolledAssignment enrolledAssignment = enrolledAssignmentRepository.findById(enrolledAssignmentId).orElse(null);
		grade.setUser(user);
		grade.setEnrolledAssignment(enrolledAssignment);
		
		int totalScore = 100;  // Total score for the entire quiz.
		int questionCount = enrolledAssignment.getCourseAssignment().getQuestions().size();
		int scorePerQuestion = totalScore / questionCount;  // Score for each question.
		int i = 0;  // Index for iterating through quizDto answers.
		int score = 0;  // Initialize the user's score.

		for (Question question : enrolledAssignment.getCourseAssignment().getQuestions()) {
		    boolean isQuestionCorrect = true;  // Assume the user answered the question correctly.

		    int j = 0;  // Index for iterating through each question's answers.
		    for (Answer answer : question.getAnswers()) {
		        // Compare user's answer with the correct answer from the database.
		        if (quizDto.getAnswerDtoList().get(i).get(j).isCorrectStatus() != answer.isCorrectStatus()) {
		            isQuestionCorrect = false;  // Mark as incorrect if any answer doesn't match.
		            break;  // No need to check further if one answer is wrong.
		        }
		        j++;
		    }

		    // Award full marks for the question if all answers are correct.
		    if (isQuestionCorrect) {
		        score += scorePerQuestion;
		    }

		    i++;
		}
		grade.setScore(score);
		grade.setGrade(score == 100 ? "S" : score >= 80 ? "A" : score >= 60 ? "B" : score >=40 ? "C" : "D");
		grade.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
		gradeRepository.save(grade);
	}

	@Override
	public List<Grade> getAllGrade() {
		return gradeRepository.findAll();
	}

	@Override
	public List<Grade> getAllGradeByStudentId(int studentId) {
		User user = userRepository.findById(studentId).orElse(null);
		List<Grade> gradeList =  gradeRepository.findAll();
		List<Grade> returnGradeList = new ArrayList<>();
		for(Grade grade : gradeList)
		{
			if(grade.getUser().getId() == user.getId())
			{
				returnGradeList.add(grade);
			}
		}
		return returnGradeList;
	}

}
