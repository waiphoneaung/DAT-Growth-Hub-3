package com.g3.elis.controller.student;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.g3.elis.model.Course;
import com.g3.elis.model.EnrolledCourse;
import com.g3.elis.model.EnrolledModule;
import com.g3.elis.model.User;
import com.g3.elis.security.LoginUserDetail;
import com.g3.elis.service.CourseService;
import com.g3.elis.service.EnrolledCourseService;
import com.g3.elis.service.EnrolledMaterialService;
import com.g3.elis.service.EnrolledModuleService;
import com.g3.elis.service.UserService;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
	 
	@Autowired
	UserService userService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private EnrolledCourseService enrolledCourseService;
	
    
    @Autowired
    private EnrolledModuleService enrolledModuleService;
    
    @Autowired
    private EnrolledMaterialService enrollMaterialService;
	
	@GetMapping("/student-dashboard")
	public String home(Model model) {
		model.addAttribute("content","student/student-dashboard");
		return "/student/student-layout";
	}
	
	@GetMapping("/blog-detail")
	public String blogDetail()
	{
		return "/authenticated-user/blog-detail";
	}
	
	
	@GetMapping("/student-course-list")
	public String getStudentCourseList(
	        @RequestParam(value = "page", defaultValue = "0") int page,
	        @RequestParam(value = "keyword", required = false) String keyword,
	        Authentication authentication,
	        Model model) {
	    
	    LoginUserDetail loginUser = (LoginUserDetail) authentication.getPrincipal();
	    User user = loginUser.getUser();

	    Pageable pageable = PageRequest.of(page, 5); // Page size of 5, adjust as needed
	    Page<EnrolledCourse> enrolledCoursesPage;

	    if (keyword != null && !keyword.isEmpty()) {
	        enrolledCoursesPage = enrolledCourseService.searchEnrolledCoursesByUser(keyword, pageable);
	    } else {
	        enrolledCoursesPage = enrolledCourseService.getEnrolledCoursesByUser(user, pageable);
	    }

	    // Calculate progress for each enrolled course
	    List<EnrolledCourse> enrolledCourses = enrolledCoursesPage.getContent();
	    for (EnrolledCourse enrolledCourse : enrolledCourses) {
	        int totalModules = enrolledCourse.getEnrolledModules().size();
	        int completedModules = (int) enrolledCourse.getEnrolledModules().stream()
	                                    .filter(EnrolledModule::isCompleteStatus)
	                                    .count();
	        // Calculate progress percentage
	        int progress = (totalModules > 0) ? (completedModules * 100) / totalModules : 0;
	        enrolledCourse.setProgress(progress);
	    }
	    
	    model.addAttribute("enrolledCourses", enrolledCourses);
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", enrolledCoursesPage.getTotalPages());
	    model.addAttribute("keyword", keyword);
	    
	    model.addAttribute("content", "student/student-course-list");
	    return "/student/student-layout";
	}

	// Continue course
    @GetMapping("/student-course-resume/{courseId}")
    public String continueCourse(@PathVariable("courseId") int courseId, Authentication authentication, Model model) {
        // Your logic for continuing the course, if any specific is needed
        return "redirect:/student/student-course-resume";
    }

    // Restart course
    @GetMapping("/student-course-restart/{courseId}")
    public String restartCourse(@PathVariable("courseId") int courseId, Authentication authentication,Model model) {
        // Fetch the course
        EnrolledCourse enrolledCourse = enrolledCourseService.getEnrolledCourseByEnrolledCourseId(courseId);
        
        // Reset statuses of enrolled modules
        for (EnrolledModule enrolledModule : enrolledCourse.getEnrolledModules()) {
            enrolledModule.setCompleteStatus(false);
            enrolledModuleService.save(enrolledModule);
        }
        
        // Reset the status of the enrolled course
        enrolledCourse.setCompleteStatus(false);
        enrolledCourse.setProgress(0);
        enrolledCourseService.save(enrolledCourse);

        // Reset the status of related enrolled materials and assignments if needed
        // This assumes you have a way to fetch these by enrolledCourse
        enrollMaterialService.resetMaterialsStatusByCourse(enrolledCourse.getId());
        
        model.addAttribute("content", "student/student-course-list");
	    
        return "redirect:/student/student-layout";
    }

	@GetMapping("/student-view-allcourses")
	public String studentCourseList(Model model,
	                                @RequestParam(name = "page", defaultValue = "0") int page,
	                                @RequestParam(name = "size", defaultValue = "10") int size,
	                                @RequestParam(name = "keyword", required = false) String keyword) {

	    Page<Course> coursePage;
	    if (keyword != null && !keyword.isEmpty()) {
	        coursePage = courseService.searchCoursesByTitle(keyword, PageRequest.of(page, size));
	    } else {
	        coursePage = courseService.getPaginatedCourses(PageRequest.of(page, size));
	    }

	    // Fetch module counts using the service method
	    Map<Integer, Long> courseModuleCounts = courseService.countCourseModulesForCourses(coursePage);

	    int totalCourses = (int) coursePage.getTotalElements();
	    int activatedCourse = (int) coursePage.getContent().stream()
	            .filter(course -> "Activated".equalsIgnoreCase(course.getStatus())).count();
	    int pendingCourse = (int) coursePage.getContent().stream()
	            .filter(course -> "Pending".equalsIgnoreCase(course.getStatus())).count();

	    model.addAttribute("totalCourse", totalCourses);
	    model.addAttribute("activatedCourse", activatedCourse);
	    model.addAttribute("pendingCourse", pendingCourse);
	    model.addAttribute("courses", coursePage.getContent());
	    model.addAttribute("courseModuleCounts", courseModuleCounts);
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", coursePage.getTotalPages());

	    return "/student/student-view-allcourses";
	}

	@GetMapping("/instructor-list")
	public String instructorList(@RequestParam(value = "page", defaultValue = "0") int page, Model model) {
	    Pageable pageable = PageRequest.of(page, 8);
	    Page<User> userPage = userService.getAllInstructors(pageable);
	    model.addAttribute("users", userPage.getContent());
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", userPage.getTotalPages());
	    model.addAttribute("content", "student/instructor-list");
	    return "/student/student-layout";
	}

	@GetMapping("/blog_detail")
	public String BlogDetail(Model model) {
		
		return "/authenticated-user/blog-detail";
	}
}