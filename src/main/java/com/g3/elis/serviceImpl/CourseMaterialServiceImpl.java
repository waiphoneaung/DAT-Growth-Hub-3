package com.g3.elis.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g3.elis.model.CourseMaterial;
import com.g3.elis.repository.CourseMaterialRepository;
import com.g3.elis.service.CourseMaterialService;

@Service
public class CourseMaterialServiceImpl implements CourseMaterialService{

	@Autowired
	private CourseMaterialRepository courseMaterialRepository;
	
	@Override
	public List<CourseMaterial> getAllCourseMaterialByCourseModuleId(int courseModuleId) {
		List<CourseMaterial> courseMaterialList = new ArrayList<CourseMaterial>();
		List<CourseMaterial> courseMaterialListFromDb = courseMaterialRepository.findAll();
		for(CourseMaterial courseMaterial : courseMaterialListFromDb)
		{
			if(courseModuleId == courseMaterial.getCourseModules().getId())
			{
				courseMaterialList.add(courseMaterial);
			}
		}
		
		return courseMaterialList;
	}

	@Override
	public void deleteAllByCourseModuleId(int courseModuleId) {
		List<CourseMaterial> courseList = courseMaterialRepository.findAll();
		for(CourseMaterial courseMaterial : courseList)
		{
			if(courseMaterial.getCourseModules().getId() == courseModuleId)
			{
				courseMaterialRepository.delete(courseMaterial);
			}
		}
	}

}
