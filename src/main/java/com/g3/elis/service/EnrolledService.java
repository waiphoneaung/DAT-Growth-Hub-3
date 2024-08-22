package com.g3.elis.service;

import java.util.List;

import com.g3.elis.model.User;

public interface EnrolledService {
	void enrollStudents(List<User>userList,int courseId);
	void enrollStudent(int userId,int courseId);
}
