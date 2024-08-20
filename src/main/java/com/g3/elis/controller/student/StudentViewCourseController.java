package com.g3.elis.controller.student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.g3.elis.config.FileStorageConfig;
import com.g3.elis.dto.form.CourseDto;
import com.g3.elis.model.Course;
import com.g3.elis.service.CourseService;
import com.g3.elis.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/student")

public class StudentViewCourseController {
	
	
		
		    private final String HTML_FILE_PATH = "src/main/resources/static/private/course/course-files/";
		    private final String IMAGE_FILE_PATH = "src/main/resources/static/private/course/course-images";

		    @Autowired
		    private CourseService courseService;

		    @Autowired
		    private UserService userService;

		    @Autowired
		    private FileStorageConfig fileStorageConfig;

		    // GET Mapping to view all courses
		    @GetMapping("/student-view-course")
		    public String studentViewCourse(@RequestParam(defaultValue = "0") int page,
		    		@RequestParam(required = false) String keyword,
		    		Model model) {
		    	int pageSize = 8; // Set the page size to 8
		        Pageable pageable = PageRequest.of(page, pageSize);
		        
		        Page<Course> coursePage;
		        
		        if (keyword != null && !keyword.isEmpty()) {
		            coursePage = CourseService.searchCourseByTitle(keyword, pageable);
		            model.addAttribute("keyword", keyword); // To preserve the search term in the search bar
		        } else {
		            coursePage = CourseService.getAllCourses(pageable);
		        }
		        

		        int previousPage = (page > 0) ? page - 1 : 0;
		        int nextPage = (page < coursePage.getTotalPages() - 1)? page + 1 : coursePage.getTotalPages() - 1;

		        model.addAttribute("courses", coursePage);
		        model.addAttribute("currentPage", page);
		        model.addAttribute("nextPage", nextPage);
		        model.addAttribute("previousPage", previousPage);
		        model.addAttribute("totalPages", coursePage.getTotalPages());
		        
	        model.addAttribute("content", "student/student-view-allcourses");
		        return "/student/student-layout";
		    }

		    // GET Mapping to view blog details
		    @GetMapping("/course-detail/{id}")
		    public String studentViewCourseDetail(@PathVariable("id") int id, Model model) throws IOException {
		        Course course = CourseService.findById(id);

		        if (course != null) {
		            LocalDateTime createdAt = course.getCreatedAt().toLocalDateTime();
		            LocalDateTime now = LocalDateTime.now();
		            Duration duration = Duration.between(createdAt, now);
		            long days = duration.toDays();
		            long hours = duration.toHours() % 24;
		            long minutes = duration.toMinutes() % 60;
		            String timeAgo = String.format("%d days, %d hours, %d minutes ago", days, hours, minutes);

		            model.addAttribute("timeAgo", timeAgo);

		            // Read HTML file content
		            java.nio.file.Path htmlFilePath = Paths.get(HTML_FILE_PATH + course.getHtmlFileName());
		            String htmlContent = Files.readString(htmlFilePath);
		            model.addAttribute("htmlContent", htmlContent);
		            model.addAttribute("course", course);
		            return "/authenticated-user/course-detail";
		        }

		        return "redirect:/student/student-view-allcourses";
		    }

		    // GET Mapping to return the HTML content of a blog file
		    @GetMapping("/course-html/{fileName}")
		    @ResponseBody
		    public String getCourseHtml(@PathVariable String fileName) throws IOException {
		        java.nio.file.Path htmlFilePath = Paths.get(HTML_FILE_PATH + fileName);
		        return Files.readString(htmlFilePath);
		    }

		    // POST Mapping to handle blog creation or update
		    @PostMapping("/student-view-allcourses")
		    public String saveCourse(@Valid CourseDto courseDto, MultipartFile imgFile, String content) {
		        try {
		            // Handle image file upload
		            if (!imgFile.isEmpty()) {
		                String originalFileName = imgFile.getOriginalFilename();
		                String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));
		                String newImageFileName = dateTime + "_" + originalFileName;
		                String imagePath = IMAGE_FILE_PATH + newImageFileName;

		                Files.write(Paths.get(imagePath), imgFile.getBytes());
		                courseDto.setImageFile(newImageFileName);
		            }

		            // Handle HTML file upload
		            Course course;
		            if (courseDto.getId() > 0) {
		                course = CourseService.findById(courseDto.getId());
		                if (course == null) {
		                    return "redirect:/edit/{id}";
		                }
		            } else {
		                course = new Course();
		                String fileName = UUID.randomUUID().toString() + ".html";
		                courseDto.setHtmlFileName(fileName);
		            }

		            String filePath = HTML_FILE_PATH + courseDto.getHtmlFileName();
		            if (Files.exists(Paths.get(filePath))) {
		                courseDto.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		                Files.write(Paths.get(filePath), content.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
		            } else {
		                courseDto.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		                courseDto.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		                courseDto.setUsers(userService.getCurrentUser());
		                Files.write(Paths.get(filePath), content.getBytes());
		            }

		            CourseService.saveCourse(courseDto);
		            return "redirect:/student/student-view-allcourses";

		        } catch (IOException e) {
		            e.printStackTrace();
		            return "redirect:/student/student-view-allcourses";
		        }
		    }
		}






