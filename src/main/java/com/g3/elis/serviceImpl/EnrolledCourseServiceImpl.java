package com.g3.elis.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g3.elis.model.Course;
import com.g3.elis.model.User;
import com.g3.elis.repository.CourseRepository;
import com.g3.elis.repository.UserRepository;
import com.g3.elis.service.EnrolledCourseService;

@Service
public class EnrolledCourseServiceImpl implements EnrolledCourseService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Override
	public List<User> findAllUserByCourseId(int courseId) {
		List<User> userList = userRepository.findAll();
		Course course = courseRepository.findById(courseId).orElse(null);
		
		List<User> userAndCourseList = new ArrayList<User>();
		for(User user : userList)
		{
			if(user.getId() == course.getId())
			{
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
		for(Course course : courseList)
		{
			if(user.getId() == course.getId())
			{
				courseAndUserList.add(course);
			}
		}
		return courseAndUserList;
	}

}
