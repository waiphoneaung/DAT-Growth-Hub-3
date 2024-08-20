package com.g3.elis.service;

import com.g3.elis.dto.form.CourseAssignmentDto;
import com.g3.elis.model.CourseAssignment;

public interface CourseAssignmentService {
	void createAssignment(CourseAssignmentDto courseAssignmentDto,int courseId,int courseModuleId);
	CourseAssignment getCourseAssignmentById(int courseAssignmentId);
	void editAssignment(CourseAssignmentDto courseAssignmentDto,int courseModuleId,int courseAssignmentId);
	void deleteAssignment(int courseModuleId,int courseAssignmentId);
}
