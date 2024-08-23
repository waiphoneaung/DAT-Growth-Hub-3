package com.g3.elis.serviceImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g3.elis.model.Course;
import com.g3.elis.model.CourseAssignment;
import com.g3.elis.model.CourseMaterial;
import com.g3.elis.model.CourseModule;
import com.g3.elis.model.EnrolledAssignment;
import com.g3.elis.model.EnrolledCourse;
import com.g3.elis.model.EnrolledMaterial;
import com.g3.elis.model.EnrolledModule;
import com.g3.elis.model.User;
import com.g3.elis.repository.CourseRepository;
import com.g3.elis.repository.EnrolledCourseRepository;
import com.g3.elis.repository.UserRepository;
import com.g3.elis.service.EnrolledService;

@Service
public class EnrolledServiceImpl implements EnrolledService {
	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private EnrolledCourseRepository enrolledCourseRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void enrollStudents(List<User> userList, int courseId) {
		Course course = courseRepository.findById(courseId).orElse(null);
		for (User user : userList) 
		{
			EnrolledCourse enrolledCourse = new EnrolledCourse();
			enrolledCourse.setUsers(user);
			enrolledCourse.setCourses(course);

			List<EnrolledModule> enrolledModules = new ArrayList<EnrolledModule>();
			for (CourseModule courseModule : course.getCourseModule()) {
				EnrolledModule enrolledModule = new EnrolledModule();
				enrolledModule.setCourseModule(courseModule);
				enrolledModule.setEnrolledCourse(enrolledCourse);
				enrolledModules.add(enrolledModule);

				List<EnrolledMaterial> enrolledMaterials = new ArrayList<EnrolledMaterial>();
				for (CourseMaterial courseMaterial : courseModule.getCourseMaterials()) {
					EnrolledMaterial enrolledMaterial = new EnrolledMaterial();
					enrolledMaterial.setCourseMaterial(courseMaterial);
					enrolledMaterial.setEnrolledModule(enrolledModule);
					enrolledMaterials.add(enrolledMaterial);

				}

				List<EnrolledAssignment> enrolledAssignments = new ArrayList<EnrolledAssignment>();
				for (CourseAssignment courseAssignment : courseModule.getCourseAssignment()) {
					EnrolledAssignment enrolledAssignment = new EnrolledAssignment();
					enrolledAssignment.setEnrolledModule(enrolledModule);
					enrolledAssignment.setCourseAssignment(courseAssignment);
					enrolledAssignments.add(enrolledAssignment);
				}
				enrolledModule.setEnrolledAssignment(enrolledAssignments);
				enrolledModule.setEnrolledMaterial(enrolledMaterials);
			}
			enrolledCourse.setEnrolledAt(Timestamp.valueOf(LocalDateTime.now()));
			enrolledCourse.setEnrolledModules(enrolledModules);
			enrolledCourseRepository.save(enrolledCourse);
		}
	}

	@Override
	public void enrollStudent(int userId, int courseId) {
		Course course = courseRepository.findById(courseId).orElse(null);
		User user = userRepository.findById(userId).orElse(null);
		EnrolledCourse enrolledCourse = new EnrolledCourse();
		enrolledCourse.setUsers(user);
		enrolledCourse.setCourses(course);

		List<EnrolledModule> enrolledModules = new ArrayList<EnrolledModule>();
		for (CourseModule courseModule : course.getCourseModule()) {
			EnrolledModule enrolledModule = new EnrolledModule();
			enrolledModule.setCourseModule(courseModule);
			enrolledModule.setEnrolledCourse(enrolledCourse);
			enrolledModules.add(enrolledModule);

			List<EnrolledMaterial> enrolledMaterials = new ArrayList<EnrolledMaterial>();
			for (CourseMaterial courseMaterial : courseModule.getCourseMaterials()) {
				EnrolledMaterial enrolledMaterial = new EnrolledMaterial();
				enrolledMaterial.setCourseMaterial(courseMaterial);
				enrolledMaterial.setEnrolledModule(enrolledModule);
				enrolledMaterials.add(enrolledMaterial);

			}

			List<EnrolledAssignment> enrolledAssignments = new ArrayList<EnrolledAssignment>();
			for (CourseAssignment courseAssignment : courseModule.getCourseAssignment()) {
				EnrolledAssignment enrolledAssignment = new EnrolledAssignment();
				enrolledAssignment.setEnrolledModule(enrolledModule);
				enrolledAssignment.setCourseAssignment(courseAssignment);
				enrolledAssignments.add(enrolledAssignment);
			}
			enrolledModule.setEnrolledAssignment(enrolledAssignments);
			enrolledModule.setEnrolledMaterial(enrolledMaterials);
		}
		enrolledCourse.setEnrolledAt(Timestamp.valueOf(LocalDateTime.now()));
		enrolledCourse.setEnrolledModules(enrolledModules);
		enrolledCourseRepository.save(enrolledCourse);
	}

}
