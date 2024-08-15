package com.g3.elis.service;

import java.util.List;

import com.g3.elis.model.CourseMaterial;

public interface CourseMaterialService {
	List<CourseMaterial> getAllCourseMaterialByCourseModuleId(int courseModuleId);
	void deleteAllByCourseModuleId(int courseModuleId);
}
