package com.g3.elis.service;

import java.io.IOException;
import java.util.List;

import com.g3.elis.dto.form.CourseMaterialDto;
import com.g3.elis.model.CourseMaterial;

public interface CourseMaterialService {
	List<CourseMaterial> getAllCourseMaterialByCourseModuleId(int courseModuleId);
	void deleteAllByCourseModuleId(int courseModuleId);
	CourseMaterial getCourseMaterialById(int materialId);
	void createCourseMaterial(CourseMaterialDto courseMaterialDto,int courseModuleId) throws IOException;
	void editCourseMaterial(CourseMaterialDto courseMaterialDto,int courseMaterialId,int courseModuleId) throws IOException;
	void deleteCourseMaterialById(int courseMaterialId);
}
