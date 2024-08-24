package com.g3.elis.controller.admin;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.g3.elis.dto.form.EnrolledCourseDto;
import com.g3.elis.model.Course;
import com.g3.elis.model.User;
import com.g3.elis.security.LoginUserDetail;
import com.g3.elis.service.CourseService;
import com.g3.elis.service.EnrolledService;
import com.g3.elis.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminCourseListController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private EnrolledService enrollService;
	
	@GetMapping("/admin-course-list")
	public String adminCourseList(Model model,
	                              @RequestParam(name = "page", defaultValue = "0") int page,
	                              @RequestParam(name = "size", defaultValue = "5") int size,
	                              @RequestParam(name = "keyword", required = false) String keyword) {

	    Page<Course> coursePage;
	    if (keyword != null && !keyword.isEmpty()) {
	        coursePage = courseService.searchCoursesByTitle(keyword, PageRequest.of(page, size));
	    } else {
	        coursePage = courseService.getPaginatedCourses(PageRequest.of(page, size));
	    }

	    int totalCourses = (int) coursePage.getTotalElements();
	    int activatedCourse = (int) coursePage.getContent().stream()
	        .filter(course -> "Activated".equalsIgnoreCase(course.getStatus())).count();
	    int pendingCourse = (int) coursePage.getContent().stream()
	        .filter(course -> "Pending".equalsIgnoreCase(course.getStatus())).count();

	    model.addAttribute("totalCourse", totalCourses);
	    model.addAttribute("activatedCourse", activatedCourse);
	    model.addAttribute("pendingCourse", pendingCourse);
	    model.addAttribute("courses", coursePage.getContent());
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", coursePage.getTotalPages());
	    model.addAttribute("content", "admin/admin-course-list");
	    return "/admin/admin-layout";
	}

	@GetMapping("/admin-course-list/approve")
	public String adminApproveRequest(@RequestParam(name="courseId")int courseId)
	{
		courseService.editCourse(courseId, "Activated");
		return "redirect:/admin/admin-course-list";
	}
	
	@GetMapping("/admin-course-list/reject")
	public String adminRejectRequest(@RequestParam(name="courseId")int courseId)
	{
		courseService.editCourse(courseId, "Rejected");
		return "redirect:/admin/admin-course-list";
	}
	
	@GetMapping("/admin-course-list/delete")
	public String adminDeleteCourse(@RequestParam(name="courseId")int courseId) throws IOException
	{
		courseService.deleteCourse(courseId);
		return "redirect:/admin/admin-course-list";
	}
	
	@GetMapping("/admin-course-list/enroll-student")
	public String adminEnrollStudent(@RequestParam(value = "page", defaultValue = "0") int page,
            						 @RequestParam(value = "size", defaultValue = "10") int size,
            						 @RequestParam(name="courseId")int courseId,Model model)
	{
		Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<User> userPage = userService.getAllStudents(pageable);
        String action = "enroll";
        EnrolledCourseDto enrolledCourseDto = new EnrolledCourseDto();
        model.addAttribute("enrolledCourseDto",enrolledCourseDto);
        model.addAttribute("action",action);
        model.addAttribute("courseId",courseId);
        model.addAttribute("users", userPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", userPage.getTotalPages());
        model.addAttribute("content", "admin/admin-student-list");
        return "/admin/admin-layout";
	}
	
	@PostMapping("/admin-course-list/enroll-student")
	public String submitEnrollStudent(@ModelAttribute("enrolledCourseDto")EnrolledCourseDto enrolledCourseDto,
									  @RequestParam(name = "courseId")int courseId)
	{
		enrollService.enrollStudents(enrolledCourseDto.getUserList(), courseId);
		return "redirect:/admin/admin-course-list";
	}
	
	@GetMapping("/admin-course-list/enroll-student/search")
	public String adminStudentListSearch(@RequestParam("search") String name, 
	        							 @RequestParam(value = "page", defaultValue = "0") int page,
	        							 @RequestParam(value = "size", defaultValue = "10") int size,
	        							 @RequestParam(name = "courseId")int courseId,
	        							 @RequestParam(name = "action")String action,
	        							 Model model) {

	    Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
	    Page<User> userPage = userService.searchUsersByName(name, pageable);
	    List<User> userList = new ArrayList<User>();
        EnrolledCourseDto enrolledCourseDto = new EnrolledCourseDto();
        model.addAttribute("enrolledCourseDto",enrolledCourseDto);
        model.addAttribute("userList",userList);
	    model.addAttribute("action",action);
        model.addAttribute("courseId",courseId);
	    model.addAttribute("users", userPage.getContent());
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", userPage.getTotalPages());
	    model.addAttribute("search", name); // Pass the search term to the view
	    model.addAttribute("size", size);   // Pass the page size to the view
	    model.addAttribute("content", "admin/admin-student-list");
	    return "/admin/admin-layout";
	}

	
	@GetMapping("/admin-course-detail")
	public String adminCourseDetail(Model model)
	{
		return "/admin/admin-course-detail";
	}
	
}