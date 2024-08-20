package com.g3.elis.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g3.elis.model.User;
import com.g3.elis.repository.EnrolledCourseRepository;
import com.g3.elis.repository.EnrolledMaterialRepository;
import com.g3.elis.repository.EnrolledModuleRepository;
import com.g3.elis.service.EnrollService;

@Service
public class EnrollServiceImpl implements EnrollService 
{
	@Autowired
	private EnrolledCourseRepository enrolledCourseRepository;
	
	@Autowired
	private EnrolledModuleRepository enrolledModuleRepository;
	
	@Autowired
	private EnrolledMaterialRepository enrolledMaterialRepository;
	
	@Override
	public void enrollStudents(List<User> userList, int courseId) 
	{
		
	}

	@Override
	public void enrollStudent(User user, int courseId) 
	{
		
	}

}
