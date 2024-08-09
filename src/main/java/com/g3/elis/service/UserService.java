package com.g3.elis.service;

import java.util.List;

import com.g3.elis.dto.form.UserDto;
<<<<<<< HEAD

=======
>>>>>>> 5763b4c43e1503ec5425ec1721f7e2e2f88448eb
import com.g3.elis.model.User;

public interface UserService {
	List<User> getAllUsers();

	List<User> getAllStudents();

	List<User> getAllInstructors();

	List<User> getAllAdmins();

	List<User> searchUsersByName(String name);

	User getUserById(int id);

	void createUser(UserDto userDto);

	public List<String> getEmailsByRole(String role);

	public List<User> searchInstructorByName(String name);

	public List<User> searchInstructors(String name, String staffId, String dept, String division);

	public void updateUserStatus(int id, boolean enabled);

}
