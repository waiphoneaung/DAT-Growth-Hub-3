package com.g3.elis.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import org.springframework.stereotype.Service;

import com.g3.elis.dto.form.CourseCategoryDto;
import com.g3.elis.model.Course;
import com.g3.elis.model.CourseCategory;

@Service
public interface CourseCategoryService {
	public void saveCourseCategory(CourseCategory courseCategory);

	List<CourseCategory> getAllCourseCategories();

	void deleteCourseCategoryById(Long id);

	public CourseCategory getCourseCategoryById(int id);

	void updateCourseCategory(int id, CourseCategoryDto courseCategoryDto);

	public List<CourseCategory> searchCourseCategoriesByName(String categoryName);

	List<CourseCategory> getCourseCategoryByUsername(String name);

	List<CourseCategory> getCourseCategoryByUserId(int id);

	Page<CourseCategory> getPaginatedCourseCategories(int page, int size);

	public Page<CourseCategory> searchPaginatedCourseCategoriesByName(String search, int page, int pageSize);
	
	public List<CourseCategory> findTop3MostEnrolledCategories();
}
