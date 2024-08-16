package com.g3.elis.serviceImpl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.g3.elis.config.FileStorageConfig;
import com.g3.elis.dto.form.CourseCreationSuperDto;
import com.g3.elis.dto.form.CourseMaterialDto;
import com.g3.elis.dto.form.CourseModuleDto;
import com.g3.elis.model.Course;
import com.g3.elis.model.CourseCategory;
import com.g3.elis.model.CourseMaterial;
import com.g3.elis.model.CourseModule;
import com.g3.elis.model.EnrolledCourse;
import com.g3.elis.model.User;
import com.g3.elis.repository.CourseCategoryRepository;
import com.g3.elis.repository.CourseRepository;
import com.g3.elis.repository.UserRepository;
import com.g3.elis.service.CourseMaterialService;
import com.g3.elis.service.CourseModuleService;
import com.g3.elis.service.CourseService;
import com.g3.elis.service.EnrolledCourseService;
import com.g3.elis.service.UserService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

	private final String courseInputFilePath = "/course/course-image-file";
	
	private final String courseInputHTMLPath = "/course/course-html-file";
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private CourseModuleService courseModuleService;
	
	@Autowired
	private CourseMaterialService courseMaterialService;
	
	@Autowired
	private EnrolledCourseService enrolledCourseService;
	
	@Autowired
	private CourseCategoryRepository courseCategoryRepository;
	
	@Autowired
	private UserRepository userRepository;
	
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
	

	
	@Override
	@Transactional
	public void editCourse(CourseCreationSuperDto superDto, User user, MultipartFile imgFile, int courseCategoryId, int courseId) throws IOException {
	    // Fetch the existing course
	    Course existingCourse = courseRepository.findById(courseId).orElse(null);
	    if (existingCourse == null) return;

	    // Preserve the existing user-course relationships
	    List<User> enrolledUsers = new ArrayList<>(enrolledCourseService.findAllUserByCourseId(courseId));

	    // Delete the old course along with its modules and materials
	    courseRepository.delete(existingCourse);

	    // Create a new course (essentially the same as the createCourse method)
	    Course newCourse = new Course();
	    newCourse.setCourseTitle(superDto.getCourseDto().getCourseTitle());
	    newCourse.setCourseDescription(superDto.getCourseDto().getCourseDescription());
	    newCourse.setCourseInfo(superDto.getCourseDto().getCourseInfo());
	    newCourse.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
	    newCourse.setUpdatedDate(Timestamp.valueOf(LocalDateTime.now()));
	    newCourse.setStatus("Pending");

	    if (superDto.getCourseDto().getDurationHour() > 0) {
	        newCourse.setDuration(superDto.getCourseDto().getDurationHour());
	    }

	    if (imgFile != null && !imgFile.isEmpty()) {
	        fileStorageConfig.saveFile(imgFile, imgFile.getOriginalFilename(), courseInputFilePath);
	        newCourse.setCourseImageFileName(imgFile.getOriginalFilename());
	    }

	    List<CourseModule> newCourseModuleList = new ArrayList<>();
	    int moduleIndex = 0;

	    for (CourseModuleDto courseModuleDto : superDto.getCourseModuleDtoList()) {
	        CourseModule courseModule = new CourseModule();
	        courseModule.setModuleTitle(courseModuleDto.getModuleTitle());
	        courseModule.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
	        courseModule.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
	        courseModule.setCourses(newCourse);

	        List<CourseMaterial> courseMaterialList = new ArrayList<>();
	        for (CourseMaterialDto courseMaterialDto : superDto.getCourseMaterialDtoList()) {
	            if (moduleIndex == courseMaterialDto.getIndex()) {
	                CourseMaterial courseMaterial = new CourseMaterial();
	                String fileName = UUID.randomUUID().toString() + ".html";
	                courseMaterial.setTitle(courseMaterialDto.getTitle());
	                courseMaterial.setContent(fileName);
	                fileStorageConfig.saveHTMLFile(courseMaterialDto.getContent(), courseInputHTMLPath, fileName);
	                courseMaterial.setCourseModules(courseModule);

	                courseMaterialList.add(courseMaterial);
	            }
	        }
	        moduleIndex++;
	        courseModule.setCourseMaterials(courseMaterialList);
	        newCourseModuleList.add(courseModule);
	    }

	    newCourse.setCourseModule(newCourseModuleList);
	    newCourse.setUsers(user);
	    newCourse.setCourseCategories(courseCategoryRepository.findById(courseCategoryId).orElse(null));

	    // Save the new course
	    courseRepository.save(newCourse);
	    
	    
	    EnrolledCourse enrolledCourse = new EnrolledCourse();
	    // Re-establish the relationships with users
	    for (User enrolledUser : enrolledUsers) {
	    	enrolledCourse.setUsers(enrolledUser);
	    	enrolledCourse.setCourses(newCourse);
	    }
	    
	    // Save users with updated course relationships
	    for (User enrolledUser : enrolledUsers) {
	        userRepository.save(enrolledUser);
	    }
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
	public Page<Course> getPaginatedCourses(Pageable pageable) {
		// TODO Auto-generated method stub
		return courseRepository.findAll(pageable);
	}



	@Override
	public Page<Course> searchCoursesByTitle(String keyword, Pageable pageable) {
		return courseRepository.findByCourseTitleContainingIgnoreCase(keyword, pageable);
	}



	
}
