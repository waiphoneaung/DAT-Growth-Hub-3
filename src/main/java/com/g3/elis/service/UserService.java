package com.g3.elis.service;

import java.util.List;

import com.g3.elis.dto.form.UserDto;
import com.g3.elis.model.User;




public interface UserService {
	List<User> getAllUsers();
	List<User> getAllStudents();
	List<User> getAllInstructors();
	List<User> getAllAdmins();
	List<User> searchUsersByName(String name);
	User getUserById(int id);
	void createUser(UserDto userDto);
	void changePassword(User user,String newPassword);
	
    public List<String> getEmailsByRole(String role);
	User getCurrentUser();
}
