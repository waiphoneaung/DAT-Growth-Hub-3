package com.g3.elis.service;

import java.util.List;

import com.g3.elis.model.Course;
import com.g3.elis.model.EnrolledCourse;
import com.g3.elis.model.User;

public interface EnrolledCourseService {
	List<User> findAllUserByCourseId(int courseId);

	List<Course> findAllCourseByUserId(int userId);

	EnrolledCourse findEnrollCourseByCourseId(int courseId);

	List<EnrolledCourse> getAllEnrolledCourse();

	List<EnrolledCourse> getAllEnrolledCourseByCourseId(int courseId);

	List<User> getEnrolledStudentsByCourseCreatedByInstructorId(int instructorId);

	List<EnrolledCourse> getAllEnrolledCourseByUserId(int userId);

	boolean isUserEnrolledToCourse(int userId, int courseId);
}
