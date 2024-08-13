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
import com.g3.elis.repository.CourseMaterialRepository;
import com.g3.elis.repository.CourseModuleRepository;
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
	private CourseModuleRepository courseModuleRepository;
	
	@Autowired
	private CourseMaterialRepository courseMaterialRepository;
	
	@Autowired
	private FileStorageConfig fileStorageConfig;
	
	@Override
	public void createCourse(CourseCreationSuperDto superDto,User user,MultipartFile imgFile) throws IOException{
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
		
		courseRepository.save(course);
		
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
			
			courseModuleRepository.save(courseModule);
			
			for (CourseMaterial courseMaterial : courseMaterialList) {
                courseMaterialRepository.save(courseMaterial);
            }
			
		}
		course.setCourseModule(courseModuleList);
		course.setUsers(user);
		courseRepository.save(course);
	}

	@Override
	public List<Course> getAllCourse() {
		return courseRepository.findAll();
	}
}
