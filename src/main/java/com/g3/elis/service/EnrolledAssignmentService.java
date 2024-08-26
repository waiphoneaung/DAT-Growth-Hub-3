package com.g3.elis.service;

import com.g3.elis.dto.form.QuizDto;
import com.g3.elis.model.EnrolledAssignment;

public interface EnrolledAssignmentService {
	EnrolledAssignment getEnrolledAssignmentById(int enrolledAssignmentId);
	boolean submitQuiz(QuizDto quizDto,int enrolledAssignmentId);
	void setStatusToTrue(int enrolledAssignmentId);
}
