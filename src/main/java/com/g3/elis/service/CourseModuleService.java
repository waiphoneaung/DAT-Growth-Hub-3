package com.g3.elis.service;

import java.util.List;

import com.g3.elis.dto.form.CourseModuleDto;
import com.g3.elis.model.CourseModule;

public interface CourseModuleService {
	List<CourseModule> getAllCourseModuleByCourseId(int courseId);
	void deleteAllByCourseId(int courseId);
	CourseModule getCourseModuleById(int moduleId);
	void createCourseModule(CourseModuleDto courseModuleDto,int courseId);
	void editCourseModule(CourseModuleDto courseModuleDto,int courseModuleId,int courseId);
	void deleteCourseModuleById(int moduleId);
	

   
}
