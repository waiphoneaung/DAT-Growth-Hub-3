package com.g3.elis.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.g3.elis.dto.form.CourseCreationSuperDto;
import com.g3.elis.dto.form.CourseDto;
import com.g3.elis.model.Course;
import com.g3.elis.model.User;

import jakarta.validation.Valid;


public interface CourseService {
	void createCourse(CourseCreationSuperDto superDto,User user,MultipartFile imgFile,int courseCategoryId)  throws IOException;
	void editCourse(int courseId,String status); //Set Status for Pending to Activated or Rejected. DO NOT DELETE THIS METHOD BY ACCIDENT
	void editCourse(CourseCreationSuperDto superDto,User user,MultipartFile imgFile,int courseCategoryId,int courseId) throws IOException;
	List<Course> getAllCourse();
	Course getCourseById(int id);
	static  Page<Course> searchCourseByTitle(String keyword, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	static  Page<Course> getAllCourses(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	static Course findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	static void saveCourse(@Valid CourseDto courseDto) {
		// TODO Auto-generated method stub
		
	}
	Page<Course> findCourses(PageRequest of);

}

