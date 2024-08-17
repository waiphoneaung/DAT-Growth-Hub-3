package com.g3.elis.serviceImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g3.elis.dto.form.CourseModuleDto;
import com.g3.elis.model.CourseMaterial;
import com.g3.elis.model.CourseModule;
import com.g3.elis.repository.CourseMaterialRepository;
import com.g3.elis.repository.CourseModuleRepository;
import com.g3.elis.repository.CourseRepository;
import com.g3.elis.service.CourseModuleService;

@Service
public class CourseModuleServiceImpl implements CourseModuleService{

	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private CourseModuleRepository courseModuleRepository;
	
	@Autowired
	private CourseMaterialRepository courseMaterialRepository;
	
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

	@Override
	public void createCourseModule(CourseModuleDto courseModuleDto,int courseId) 
	{
		CourseModule courseModule = new CourseModule();
		courseModule.setModuleTitle(courseModuleDto.getModuleTitle());
		courseModule.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
		courseModule.setCourses(courseRepository.findById(courseId).orElse(null));
		courseModuleRepository.save(courseModule);
	}

	@Override
	public void deleteCourseModuleById(int moduleId) {
	    CourseModule courseModule = courseModuleRepository.findById(moduleId).orElse(null);
	    List<CourseMaterial> deleteCourseMaterialList = new ArrayList<CourseMaterial>();
	    if (courseModule != null) 
	    {
	        courseModule.setCourses(null);
	        for (CourseMaterial courseMaterial : courseModule.getCourseMaterials()) 
	        {
	            courseMaterial.setCourseModules(null);
	            deleteCourseMaterialList.add(courseMaterial);
	        }
	        courseModule.setCourses(null);
	        courseModuleRepository.delete(courseModule);
	        
	        for(CourseMaterial courseMaterial : deleteCourseMaterialList) courseMaterialRepository.delete(courseMaterial);
	    }
	}


	@Override
	public CourseModule getCourseModuleById(int moduleId) {
		return courseModuleRepository.findById(moduleId).orElse(null);
	}

	@Override
	public void editCourseModule(CourseModuleDto courseModuleDto, int courseModuleId, int courseId) {
		CourseModule courseModule = courseModuleRepository.findById(courseModuleId).orElse(null);
		courseModule.setModuleTitle(courseModuleDto.getModuleTitle());
		courseModule.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
		courseModule.setCourses(courseRepository.findById(courseId).orElse(null));
		courseModuleRepository.save(courseModule);
	}	
}
