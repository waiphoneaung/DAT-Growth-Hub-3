package com.g3.elis.serviceImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.g3.elis.model.Course;
import com.g3.elis.model.EnrolledCourse;
import com.g3.elis.model.EnrolledModule;
import com.g3.elis.model.User;
import com.g3.elis.repository.CourseRepository;
import com.g3.elis.repository.EnrolledCourseRepository;
import com.g3.elis.repository.EnrolledModuleRepository;
import com.g3.elis.repository.UserRepository;
import com.g3.elis.service.EnrolledCourseService;

@Service
public class EnrolledCourseServiceImpl implements EnrolledCourseService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private EnrolledCourseRepository enrolledCourseRepository;

	@Autowired
	private EnrolledModuleRepository enrolledModuleRepository;

	@Override
	public List<User> findAllUserByCourseId(int courseId) {
		List<User> userList = userRepository.findAll();
		Course course = courseRepository.findById(courseId).orElse(null);

		List<User> userAndCourseList = new ArrayList<User>();
		for (User user : userList) {
			if (user.getId() == course.getId()) {
				userAndCourseList.add(user);
			}
		}
		return userAndCourseList;
	}

	@Override
	public List<Course> findAllCourseByUserId(int userId) {
		List<Course> courseList = courseRepository.findAll();
		User user = userRepository.findById(userId).orElse(null);

		List<Course> courseAndUserList = new ArrayList<Course>();
		for (Course course : courseList) {
			if (user.getId() == course.getId()) {
				courseAndUserList.add(course);
			}
		}
		return courseAndUserList;
	}

	@Override
	public EnrolledCourse findEnrollCourseByCourseId(int courseId) {
		List<EnrolledCourse> enrolledCourseList = enrolledCourseRepository.findAll();
		for (EnrolledCourse enrolledCourse : enrolledCourseList) {
			if (enrolledCourse.getCourses().getId() == courseId) {
				return enrolledCourse;
			}
		}
		return null;
	}

	@Override
	public List<EnrolledCourse> getAllEnrolledCourse() {
		return enrolledCourseRepository.findAll();
	}

	@Override
	public List<User> getEnrolledStudentsByCourseCreatedByInstructorId(int instructorId) {
		User user = userRepository.findById(instructorId).orElse(null);
		List<Course> courseList = courseRepository.findAll();
		List<User> userReturnList = new ArrayList<User>();

		for (Course course : courseList) {
			if (course.getUsers().getId() == user.getId()) {
				for (EnrolledCourse enrolledCourse : getAllEnrolledCourseByCourseId(course.getId())) {
					userReturnList.add(enrolledCourse.getUsers());
				}
			}
		}

		return userReturnList;
	}

	@Override
	public List<EnrolledCourse> getAllEnrolledCourseByCourseId(int courseId) {
		List<EnrolledCourse> enrolledCourseList = enrolledCourseRepository.findAll();
		List<EnrolledCourse> enrolledCourseReturnList = new ArrayList<EnrolledCourse>();
		for (EnrolledCourse enrolledCourse : enrolledCourseList) {
			if (enrolledCourse.getCourses().getId() == courseId) {
				enrolledCourseReturnList.add(enrolledCourse);
			}
		}
		return enrolledCourseReturnList;
	}

	@Override
	public List<EnrolledCourse> getAllEnrolledCourseByUserId(int userId) {
		List<EnrolledCourse> enrolledCourseList = enrolledCourseRepository.findAll();
		List<EnrolledCourse> enrolledCourseReturnList = new ArrayList<EnrolledCourse>();
		for (EnrolledCourse enrolledCourse : enrolledCourseList) {
			if (enrolledCourse.getUsers().getId() == userId) {
				enrolledCourseReturnList.add(enrolledCourse);
			}
		}
		return enrolledCourseReturnList;
	}
	
	@Override
	public List<EnrolledCourse> getAllEnrolledCourseByInstructorId(int instructorId) {
		List<EnrolledCourse> enrolledCourseList = enrolledCourseRepository.findAll();
		List<EnrolledCourse> enrolledCourseReturnList = new ArrayList<EnrolledCourse>();
		for(EnrolledCourse enrolledCourse : enrolledCourseList)
		{
			if(enrolledCourse.getCourses().getUsers().getId() == instructorId)
			{
				enrolledCourseReturnList.add(enrolledCourse);
			}
		}
		
		return enrolledCourseReturnList;
	}

	@Override
	public boolean isUserEnrolledToCourse(int userId, int courseId) {
		for (EnrolledCourse enrolledCourse : enrolledCourseRepository.findAll()) {
			if (enrolledCourse.getCourses().getId() == courseId && enrolledCourse.getUsers().getId() == userId) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void setStatusToTrue(int enrolledCourseId) {
		EnrolledCourse enrolledCourse = enrolledCourseRepository.findById(enrolledCourseId).orElse(null);
		for (EnrolledModule enrolledModule : enrolledCourse.getEnrolledModules()) {
			if (enrolledModule.isCompleteStatus() != true)
				return;
		}
		enrolledCourse.setCompletedAt(Timestamp.valueOf(LocalDateTime.now()));
		enrolledCourse.setCompleteStatus(true);
		enrolledCourseRepository.save(enrolledCourse);
	}

	@Override
	public EnrolledCourse getEnrolledCourseByEnrolledCourseId(int enrolledCourseId) {
		return enrolledCourseRepository.findById(enrolledCourseId).orElse(null);
	}

	public Page<EnrolledCourse> getEnrolledCoursesByUser(User users, Pageable pageable) {
		return enrolledCourseRepository.findByUsers(users, pageable);

	}

	@Override
	public void save(EnrolledCourse enrolledCourse) {
		 enrolledCourseRepository.save(enrolledCourse);
		
	}

	@Override
	public Page<EnrolledCourse> searchEnrolledCoursesByUser(String keyword, Pageable pageable) {
		return enrolledCourseRepository.findByCourses_CourseTitleContainingIgnoreCase(keyword, pageable);
	}

	}

