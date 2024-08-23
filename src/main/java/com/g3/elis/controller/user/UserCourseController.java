package com.g3.elis.controller.user;



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.g3.elis.model.Course;
import com.g3.elis.security.LoginUserDetail;
import com.g3.elis.service.CourseService;
import com.g3.elis.service.EnrolledService;
import com.g3.elis.service.EnrolledCourseService;
import com.g3.elis.service.UserService;


@Controller
@RequestMapping("/user")
public class UserCourseController {
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private EnrolledCourseService enrolledCourseService;

	@Autowired
	private EnrolledService enrollService;
	
	@Autowired
	private UserService userService;
	
//	@GetMapping("/courses")
//	public String getAllCourses(Model model) {
//	    List<Course> courses = courseService.getAllCourse();
//	    
//	    model.addAttribute("courses", courses);
//	    model.addAttribute("content","user/courses");
//	    return "/user/layout";
//	}
	
	@GetMapping("/courses")
    public String adminCourseList(Model model,
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
        model.addAttribute("content", "user/courses");
        return "/user/layout";
    }
	@GetMapping("/courses/course-detail")
	public String courseDetail(@RequestParam(name = "courseId")int courseId,
							   Authentication authentication,Model model)
	{
		LoginUserDetail userDetail = (LoginUserDetail) authentication.getPrincipal();
		Course course = courseService.getCourseById(courseId);
		model.addAttribute("isEnroll",enrolledCourseService.isUserEnrolledToCourse(userDetail.getUser().getId(),courseId));
		model.addAttribute("course",course);
		model.addAttribute("content","user/user-course-detail");
		return "/user/layout";
	}
	@GetMapping("/courses/course-detail/enroll")
	public String enrollCourse(@RequestParam(name = "courseId")int courseId,
							   Authentication authentication)
	{
		LoginUserDetail userDetail = (LoginUserDetail) authentication.getPrincipal();
		enrollService.enrollStudent(userDetail.getUser().getId(), courseId);
		return "redirect:/student/student-course-resume";
	}
}
