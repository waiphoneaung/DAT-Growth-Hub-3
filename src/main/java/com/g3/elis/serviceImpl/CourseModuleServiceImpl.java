package com.g3.elis.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g3.elis.model.CourseModule;
import com.g3.elis.repository.CourseModuleRepository;
import com.g3.elis.service.CourseModuleService;

@Service
public class CourseModuleServiceImpl implements CourseModuleService{

	
	@Autowired
	private CourseModuleRepository courseModuleRepository;
	
	@Override
	public List<CourseModule> getAllCourseModuleByCourseId(int courseId) {
		List<CourseModule> courseModuleList = new ArrayList<CourseModule>();
		List<CourseModule> courseModuleFromDb = courseModuleRepository.findAll();
		for(CourseModule courseModule : courseModuleFromDb)
		{
			if(courseModule.getCourses().getId() == courseId)
			{
				courseModuleList.add(courseModule);
			}
		}
		return courseModuleList;
	}

	@Override
	public void deleteAllByCourseId(int courseId) {
		List<CourseModule> courseModuleList = courseModuleRepository.findAll();
		for(CourseModule courseModule : courseModuleList)
		{
			if(courseModule.getCourses().getId() == courseId)
			{
				courseModuleRepository.delete(courseModule);
			}
		}
	}	
}
