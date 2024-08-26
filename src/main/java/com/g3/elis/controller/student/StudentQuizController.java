package com.g3.elis.controller.student;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.g3.elis.dto.form.AnswerDto;
import com.g3.elis.dto.form.QuestionDto;
import com.g3.elis.dto.form.QuizDto;
import com.g3.elis.model.Answer;
import com.g3.elis.model.EnrolledAssignment;
import com.g3.elis.model.Question;
import com.g3.elis.security.LoginUserDetail;
import com.g3.elis.service.AnswerService;
import com.g3.elis.service.EnrolledAssignmentService;
import com.g3.elis.service.GradeService;
import com.g3.elis.service.QuestionService;

@Controller
@RequestMapping("/student")
public class StudentQuizController 
{
	@Autowired
	private EnrolledAssignmentService enrolledAssignmentService;
	
	@Autowired
	private GradeService gradeService;

	private QuizDto populateQuizDto(int enrolledAssignmentId) {
		QuizDto quizDto = new QuizDto();
		quizDto.setAnswerDtoList(new ArrayList<>());
		for (Question question : enrolledAssignmentService.getEnrolledAssignmentById(enrolledAssignmentId)
				.getCourseAssignment().getQuestions()) {
			List<AnswerDto> answerDtoList = new ArrayList<AnswerDto>();
			for (int i = 0; i < question.getAnswers().size(); i++) {
				AnswerDto answerDto = new AnswerDto();
				answerDto.setCorrectStatus(false);
				answerDtoList.add(answerDto);
			}
			quizDto.getAnswerDtoList().add(answerDtoList);
		}
		return quizDto;
	}

	@GetMapping("/student-quiz")
	public String studentQuiz(@RequestParam(name = "enrolledAssignmentId") int enrolledAssignmentId, Model model) {

		model.addAttribute("quizDto", populateQuizDto(enrolledAssignmentId));
		model.addAttribute("enrolledAssignment", enrolledAssignmentService.getEnrolledAssignmentById(enrolledAssignmentId));
		return "/student/student-quiz";
	}

	@PostMapping("/student-quiz")
	public String submitQuiz(@ModelAttribute("quizDto") QuizDto quizDto,
						     @RequestParam(name = "enrolledAssignmentId") int enrolledAssignmentId,
						     Authentication authentication,Model model) 
	{
		LoginUserDetail userDetail = (LoginUserDetail) authentication.getPrincipal();
		gradeService.createGrade(userDetail.getUser().getId(), enrolledAssignmentId, quizDto);
		if (enrolledAssignmentService.submitQuiz(quizDto, enrolledAssignmentId)) 
		{
			enrolledAssignmentService.setStatusToTrue(enrolledAssignmentId);
		}
		model.addAttribute("content", "student/student-course-resume");
		return "redirect:/student/student-course-resume";
	}
}
