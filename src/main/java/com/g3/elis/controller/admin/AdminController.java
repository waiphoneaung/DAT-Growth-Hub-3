package com.g3.elis.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
<<<<<<< HEAD
=======
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.g3.elis.model.CourseCategory;
import com.g3.elis.model.User;
import com.g3.elis.service.CourseCategoryService;
import com.g3.elis.service.InputFileService;
import com.g3.elis.service.UserService;
>>>>>>> c603465899b5d203095dbf8e14256023d8b20271

@Controller
@RequestMapping("/admin")
public class AdminController
{
	
<<<<<<< HEAD
=======
	@Autowired
	private UserService userService;
	
	@Autowired
	private InputFileService inputFileService;
	
	@Autowired
    private CourseCategoryService courseCategoryService;
	
>>>>>>> c603465899b5d203095dbf8e14256023d8b20271
	@GetMapping("/admin-dashboard")
	public String home(Model model) {
		model.addAttribute("content","admin/admin-dashboard");
		return "/admin/admin-layout";
	}
	
	@GetMapping("/admin-course-list")
	public String adminCourseList(Model model)
	{
		model.addAttribute("content","admin/admin-course-list");
		return "/admin/admin-layout";
	}
	
<<<<<<< HEAD
	@GetMapping("/admin-course-category")
	public String adminCourseCategory(Model model)
	{
		model.addAttribute("content","admin/admin-course-category");
		return "/admin/admin-layout";
	}
	
=======
	@GetMapping("/admin-student-list")
	public String adminStudentList(Model model)
	{
		List<User> users = userService.getAllStudents();
		model.addAttribute("users",users);
		model.addAttribute("content","admin/admin-student-list");
		return "/admin/admin-layout";
	}
>>>>>>> c603465899b5d203095dbf8e14256023d8b20271
	
	@GetMapping("/admin-instructor-request")
	public String adminInstructorRequest(Model model)
	{
		model.addAttribute("content","admin/admin-instructor-request");
		return "/admin/admin-layout";
	}
	
	@GetMapping("/admin-certificate-design")
	public String adminCertificateDesign(Model model)
	{
		model.addAttribute("content","admin/admin-certificate-design");
		return "/admin/admin-layout";
	}
	@GetMapping("/admin-setting")
	public String adminSetting(Model model)
	{
		model.addAttribute("content","admin/admin-setting");
		return "/admin/admin-layout";
	}
	@GetMapping("/admin-edit-profile")
	public String adminEditProfile(Model model)
	{
		model.addAttribute("content","admin/admin-edit-profile");
		return "/admin/admin-layout";
	}
	
	@GetMapping("/admin-course-detail")
	public String adminCourseDetail(Model model)
	{
		model.addAttribute("content","admin/admin-course-detail");
		return "/admin/admin-layout";
	}
	@GetMapping("/admin-edit-course-detail")
	public String adminEditCourseDetail(Model model)
	{
		model.addAttribute("content","admin/admin-edit-course-detail");
		return "/admin/admin-layout";
	}
<<<<<<< HEAD
=======
	@GetMapping("/admin-create-blog")
	public String adminCreateBlog(Model model) {
		return "/admin/admin-create-blog";
	}
	@GetMapping("/admin-quiz")
	public String adminQuiz(Model model) {
		return "/admin/admin-quiz";
	}
	
	@GetMapping("/blog_detail")
	public String BlogDetail(Model model) {
		
		return "/authenticated-user/blog-detail";
	}
	
	
	
>>>>>>> c603465899b5d203095dbf8e14256023d8b20271
}