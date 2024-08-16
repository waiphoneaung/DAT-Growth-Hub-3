package com.g3.elis.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.g3.elis.dto.form.CourseCreationSuperDto;
import com.g3.elis.model.Course;
import com.g3.elis.model.User;


public interface CourseService {
	void createCourse(CourseCreationSuperDto superDto,User user,MultipartFile imgFile,int courseCategoryId)  throws IOException;
	void editCourse(int courseId,String status); //Set Status for Pending to Activated or Rejected. DO NOT DELETE THIS METHOD BY ACCIDENT
	void editCourse(CourseCreationSuperDto superDto,User user,MultipartFile imgFile,int courseCategoryId,int courseId) throws IOException;
	void deleteCourse(int courseId);
	List<Course> getAllCourse();
	Course getCourseById(int id);
}
