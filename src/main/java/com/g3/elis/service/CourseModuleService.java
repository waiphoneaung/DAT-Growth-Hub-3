package com.g3.elis.service;

import java.util.List;

import com.g3.elis.model.CourseModule;

public interface CourseModuleService {
	List<CourseModule> getAllCourseModuleByCourseId(int courseId);
	void deleteAllByCourseId(int courseId);
}
