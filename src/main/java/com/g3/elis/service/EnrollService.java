package com.g3.elis.service;

import java.util.List;

import com.g3.elis.model.User;

public interface EnrollService {
	void enrollStudents(List<User>userList,int courseId);
	void enrollStudent(User user,int courseId);
}
