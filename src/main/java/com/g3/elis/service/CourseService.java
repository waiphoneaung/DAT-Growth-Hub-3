package com.g3.elis.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.g3.elis.dto.form.CourseCreationSuperDto;
import com.g3.elis.model.Course;
import com.g3.elis.model.CourseCategory;
import com.g3.elis.model.User;

public interface CourseService {
	void createCourse(CourseCreationSuperDto superDto, User user, MultipartFile imgFile, int courseCategoryId)
			throws IOException;

	void editCourse(int courseId, String status); // Set Status for Pending to Activated or Rejected. DO NOT DELETE THIS
													// METHOD BY ACCIDENT

	void editCourse(CourseCreationSuperDto superDto, User user, MultipartFile imgFile, int courseCategoryId,
			int courseId) throws IOException;

	void deleteCourse(int courseId) throws IOException;

	List<Course> getAllCourse();
	
	List<Course> getAllPendingCourse();
	
	List<Course> getAllCourseByUserId(int userId);

	Course getCourseById(int id);

	 Page<Course> getPaginatedCourses(Pageable pageable);
	 Page<Course> searchCoursesByTitle(String keyword, Pageable pageable);
	
	 Map<Integer, Long> countCourseModulesForCourses(Page<Course> coursePage);
	 
	 public long countAllCourses();
	 
	 public List<Course> findCoursesByCategory(int categoryId);

}
