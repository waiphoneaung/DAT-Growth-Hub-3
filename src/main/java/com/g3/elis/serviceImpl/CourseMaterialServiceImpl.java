package com.g3.elis.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g3.elis.config.FileStorageConfig;
import com.g3.elis.dto.form.CourseMaterialDto;
import com.g3.elis.model.CourseMaterial;
import com.g3.elis.repository.CourseMaterialRepository;
import com.g3.elis.repository.CourseModuleRepository;
import com.g3.elis.service.CourseMaterialService;

@Service
public class CourseMaterialServiceImpl implements CourseMaterialService{

	private final String courseInputHTMLPath = "/course/course-html-file";
	
	@Autowired
	private CourseMaterialRepository courseMaterialRepository;
	
	@Autowired
	private CourseModuleRepository courseModuleRepository;
	
	@Autowired
	private FileStorageConfig fileStorageConfig;
	
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

	@Override
	public void createCourseMaterial(CourseMaterialDto courseMaterialDto, int courseModuleId) throws IOException {
		CourseMaterial courseMaterial = new CourseMaterial();
		courseMaterial.setTitle(courseMaterialDto.getTitle());
//		String fileName = UUID.randomUUID().toString() + ".html";
//		courseMaterial.setContent(fileName);
		courseMaterial.setCourseModules(courseModuleRepository.findById(courseModuleId).orElse(null));
//		fileStorageConfig.saveHTMLFile(courseMaterialDto.getContent(), courseInputHTMLPath, fileName);
		courseMaterialRepository.save(courseMaterial);
	}

	@Override
	public void deleteCourseMaterialById(int courseMaterialId) 
	{
		CourseMaterial courseMaterial = courseMaterialRepository.findById(courseMaterialId).orElse(null);
		courseMaterial.setCourseModules(null);
		courseMaterialRepository.delete(courseMaterial);
	}

	@Override
	public CourseMaterial getCourseMaterialById(int materialId) {
		return courseMaterialRepository.findById(materialId).orElse(null);
	}

	@Override
	public void editCourseMaterial(CourseMaterialDto courseMaterialDto, int courseMaterialId, int courseModuleId) throws IOException {
		CourseMaterial courseMaterial = courseMaterialRepository.findById(courseMaterialId).orElse(null);
		courseMaterial.setTitle(courseMaterialDto.getTitle());
//		String fileName = UUID.randomUUID().toString() + ".html";
//		courseMaterial.setContent(fileName);
		courseMaterial.setCourseModules(courseModuleRepository.findById(courseModuleId).orElse(null));
//		fileStorageConfig.saveHTMLFile(courseMaterialDto.getContent(), courseInputHTMLPath, fileName);
		courseMaterialRepository.save(courseMaterial);
	}
}
