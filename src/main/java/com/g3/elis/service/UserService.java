package com.g3.elis.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import com.g3.elis.dto.form.UserDto;
import com.g3.elis.model.Course;
import com.g3.elis.model.CourseCategory;
import com.g3.elis.model.User;

public interface UserService {
	List<User> getAllUsers();

	public List<String> getEmailsByRole(String role);

	User getCurrentUser();

	User getUserById(int id);

	void createUser(UserDto userDto);

	public void updateUserStatus(int id, boolean enabled);

	Page<User> getAllStudents(Pageable pageable);

	Page<User> searchUsersByName(String name, Pageable pageable);

	Page<User> getAllInstructors(Pageable pageable);

	Page<User> searchInstructors(String name, String staffId, String dept, String division, Pageable pageable);

	void changePassword(User user, String newPassword);
	
	public boolean isUserEnrolled(String username, Long courseId);
	
	public long countAllInstructor();
	
	public long countAllStudent();
}
