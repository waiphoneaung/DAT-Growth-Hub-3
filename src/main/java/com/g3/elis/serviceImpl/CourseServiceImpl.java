package com.g3.elis.serviceImpl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.g3.elis.config.FileStorageConfig;
import com.g3.elis.dto.form.CourseCreationSuperDto;
import com.g3.elis.dto.form.CourseMaterialDto;
import com.g3.elis.dto.form.CourseModuleDto;
import com.g3.elis.model.Course;
import com.g3.elis.model.CourseMaterial;
import com.g3.elis.model.CourseModule;
import com.g3.elis.model.User;
import com.g3.elis.repository.CourseCategoryRepository;
import com.g3.elis.repository.CourseRepository;
import com.g3.elis.service.CourseService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

	private final String courseInputFilePath = "/course/course-image-file";
	
	private final String courseInputHTMLPath = "/course/course-html-file";
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private CourseCategoryRepository courseCategoryRepository;
	
	@Autowired
	private FileStorageConfig fileStorageConfig;
	
	@Override
	@Transactional
	public void createCourse(CourseCreationSuperDto superDto,User user,MultipartFile imgFile,int courseCategoryId) throws IOException
	{
		
		Course course = new Course();
		
		course.setCourseTitle(superDto.getCourseDto().getCourseTitle());
		course.setCourseDescription(superDto.getCourseDto().getCourseDescription());
		course.setCourseInfo(superDto.getCourseDto().getCourseInfo());
		course.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
		course.setUpdatedDate(Timestamp.valueOf(LocalDateTime.now()));
		course.setStatus("Pending");
		if(superDto.getCourseDto().getDurationHour()>0)
		{
			course.setDuration(superDto.getCourseDto().getDurationHour());
		}
		if(!(imgFile.isEmpty())|| imgFile != null)
		{
			fileStorageConfig.saveFile(imgFile, imgFile.getOriginalFilename(), courseInputFilePath);
			course.setCourseImageFileName(imgFile.getOriginalFilename());
		}
		
		List<CourseModule> courseModuleList = new ArrayList<>();
		int index = 0;
		
		for(CourseModuleDto courseModuleDto : superDto.getCourseModuleDtoList())
		{
			
			CourseModule courseModule = new CourseModule();
			courseModule.setModuleTitle(courseModuleDto.getModuleTitle());
			
			List<CourseMaterial> courseMaterialList = new ArrayList<>();
			
			for(CourseMaterialDto courseMaterialDto : superDto.getCourseMaterialDtoList())
			{
				if(index == courseMaterialDto.getIndex())
				{
					CourseMaterial courseMaterial = new CourseMaterial();
					String fileName = UUID.randomUUID().toString() + ".html";
					courseMaterial.setTitle(courseMaterialDto.getTitle());
					courseMaterial.setContent(fileName);
					courseMaterial.setCourseModules(courseModule);
					
					fileStorageConfig.saveHTMLFile(courseMaterialDto.getContent(), courseInputHTMLPath, fileName);
					
					courseMaterialList.add(courseMaterial);
				}
			}
			index++;
			
			courseModule.setCourseMaterials(courseMaterialList);
			courseModule.setCourses(course);
			courseModule.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
			courseModule.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
			courseModuleList.add(courseModule);
			
		}
		course.setCourseModule(courseModuleList);
		course.setUsers(user);
		course.setCourseCategories(courseCategoryRepository.findById(courseCategoryId).orElse(null));
		courseRepository.save(course);
	}
	
//	@Override
//	@Transactional
//	public void editCourse(CourseCreationSuperDto superDto, User user, MultipartFile imgFile, int courseCategoryId, int courseId) throws IOException 
//	{
//		Course course = courseRepository.findById(courseId).orElse(null);
//		if(course== null) return;
//		course.setCourseTitle(superDto.getCourseDto().getCourseTitle());
//		course.setCourseDescription(superDto.getCourseDto().getCourseDescription());
//		course.setCourseInfo(superDto.getCourseDto().getCourseInfo());
//		course.setUpdatedDate(Timestamp.valueOf(LocalDateTime.now()));
//		course.setStatus("Pending");
//		if(superDto.getCourseDto().getDurationHour()>0)
//		{
//			course.setDuration(superDto.getCourseDto().getDurationHour());
//		}
//		if(!(imgFile.isEmpty())|| imgFile != null)
//		{
//			fileStorageConfig.saveFile(imgFile, imgFile.getOriginalFilename(), courseInputFilePath);
//			course.setCourseImageFileName(imgFile.getOriginalFilename());
//		}
//		
//		List<CourseModule> courseModuleList = new ArrayList<>();
//		List<CourseModule> courseModuleListFromCourse = course.getCourseModule();
//		int courseModuleIterationIndex = 0;
//		
//		for(CourseModuleDto courseModuleDto : superDto.getCourseModuleDtoList())
//		{
//			CourseModule courseModule = new CourseModule();
//			if(courseModuleIterationIndex > courseModuleListFromCourse.size())
//			{
//			CourseModule courseModule;
//			if(courseModuleIterationIndex > courseModuleListFromCourse.size())
//			{
//				courseModule = new CourseModule();
//				courseModule.setCourses(course);
//				courseModule.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
//			}
//			else
//			{	
//				courseModule = courseModuleListFromCourse.get(courseModuleIterationIndex);
//			}
//			courseModule.setModuleTitle(courseModuleDto.getModuleTitle());
//			
//			List<CourseMaterial> courseMaterialList = new ArrayList<>();
//			List<CourseMaterial> courseMaterialListFromCourseModule = courseModule.getCourseMaterials();
//			
//			for(CourseMaterialDto courseMaterialDto : superDto.getCourseMaterialDtoList())
//			{
//				int courseMaterialIterationIndex = 0;
//				if(courseModuleIterationIndex == courseMaterialDto.getIndex())
//				{
//					CourseMaterial courseMaterial;
//					if(courseMaterialIterationIndex > courseMaterialListFromCourseModule.size())
//					{
//						courseMaterial = new CourseMaterial();
//						courseMaterial.setCourseModules(courseModule);
//					}
//					else
//					{
//						courseMaterial = courseMaterialListFromCourseModule.get(courseMaterialIterationIndex);
//					}
//					String fileName = UUID.randomUUID().toString() + ".html";
//					courseMaterial.setTitle(courseMaterialDto.getTitle());
//					courseMaterial.setContent(fileName);
//					courseMaterial.setCourseModules(courseModule);
//					
//					fileStorageConfig.saveHTMLFile(courseMaterialDto.getContent(), courseInputHTMLPath, fileName);
//					
//					courseMaterialList.add(courseMaterial);
//				}
//				courseMaterialIterationIndex++;
//			}
//			courseModuleIterationIndex++;
//			
//			courseModule.setCourseMaterials(courseMaterialList);
//			courseModule.getCourseMaterials().clear();
//			courseModule.setCourses(course);
//			courseModule.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
//			courseModuleList.add(courseModule);
//
//		}
//		course.setCourseModule(courseModuleList);
//		course.setUsers(user);
//		course.setCourseCategories(courseCategoryRepository.findById(courseCategoryId).orElse(null));
//		courseRepository.save(course);
//	}
	
	@Override
	// @Transactional
	public void editCourse(CourseCreationSuperDto superDto, User user, MultipartFile imgFile, int courseCategoryId, int courseId) throws IOException 
	{
	    Course course = courseRepository.findById(courseId).orElse(null);
	    if(course == null) return;

	    course.setCourseTitle(superDto.getCourseDto().getCourseTitle());
	    course.setCourseDescription(superDto.getCourseDto().getCourseDescription());
	    course.setCourseInfo(superDto.getCourseDto().getCourseInfo());
	    course.setUpdatedDate(Timestamp.valueOf(LocalDateTime.now()));
	    course.setStatus("Pending");
	    
	    if(superDto.getCourseDto().getDurationHour() > 0) {
	        course.setDuration(superDto.getCourseDto().getDurationHour());
	    }
	    
	    if(imgFile != null && !imgFile.isEmpty()) {
	        fileStorageConfig.saveFile(imgFile, imgFile.getOriginalFilename(), courseInputFilePath);
	        course.setCourseImageFileName(imgFile.getOriginalFilename());
	    }

	    List<CourseModule> existingModules = course.getCourseModule();
	    List<CourseModule> updatedModules = new ArrayList<>();

	    int moduleIndex = 0;
	    for (CourseModuleDto courseModuleDto : superDto.getCourseModuleDtoList()) 
	    {
	        CourseModule courseModule;
	        if (moduleIndex < existingModules.size()) 
	        {
	            courseModule = existingModules.get(moduleIndex);
	        } 
	        else 
	        {
	            courseModule = new CourseModule();
	            courseModule.setCourses(course);
	            courseModule.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
	        }

	        courseModule.setModuleTitle(courseModuleDto.getModuleTitle());
	        courseModule.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

	        List<CourseMaterial> existingMaterials = courseModule.getCourseMaterials();
	        List<CourseMaterial> updatedMaterials = new ArrayList<>();

	        int materialIndex = 0;
	        for (CourseMaterialDto courseMaterialDto : superDto.getCourseMaterialDtoList()) 
	        {
	            if (courseMaterialDto.getIndex() == moduleIndex) 
	            {
	                CourseMaterial courseMaterial;
	                if (materialIndex < existingMaterials.size()) 
	                {
	                    courseMaterial = existingMaterials.get(materialIndex);
	                } 
	                else 
	                {
	                    courseMaterial = new CourseMaterial();
	                    courseMaterial.setCourseModules(courseModule);
	                }
	                String fileName = UUID.randomUUID().toString() + ".html";
	                courseMaterial.setTitle(courseMaterialDto.getTitle());
	                courseMaterial.setContent(fileName);
	                fileStorageConfig.saveHTMLFile(courseMaterialDto.getContent(), courseInputHTMLPath, fileName);
	                updatedMaterials.add(courseMaterial);
	                materialIndex++;
	            }
	        }

	        courseModule.getCourseMaterials().clear();  // Clear old materials to handle orphan removal
	        courseModule.getCourseMaterials().addAll(updatedMaterials);

	        updatedModules.add(courseModule);
	        moduleIndex++;
	    }

	    course.getCourseModule().clear();  // Clear old modules to handle orphan removal
	    course.getCourseModule().addAll(updatedModules);

	    course.setUsers(user);
	    course.setCourseCategories(courseCategoryRepository.findById(courseCategoryId).orElse(null));

	    courseRepository.save(course);
	}

	// Set Status for Pending to Activated or Rejected
	// DO NOT DELETE THIS METHOD BY ACCIDENT
	@Override
	public void editCourse(int courseId,String status) {
		Course course = courseRepository.findById(courseId).orElse(null);
		course.setStatus(status);
		courseRepository.save(course);
	}
	
	@Override
	public List<Course> getAllCourse() {
		return courseRepository.findAll();
	}

	@Override
	public Course getCourseById(int id) {
		return courseRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteCourse(int courseId) {
		courseRepository.deleteById(courseId);
	}
	
}
