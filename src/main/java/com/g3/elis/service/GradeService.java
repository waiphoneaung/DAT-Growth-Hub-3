package com.g3.elis.service;

import java.util.List;

import com.g3.elis.dto.form.QuizDto;
import com.g3.elis.model.Grade;

public interface GradeService {
	void createGrade(int userId,int enrolledAssignmentId,QuizDto quizDto);
	List<Grade> getAllGrade();
	List<Grade> getAllGradeByStudentId(int studentId);
}
