package com.g3.elis.controller.admin;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.g3.elis.model.User;
import com.g3.elis.service.InputFileService;
import com.g3.elis.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController
{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private InputFileService inputFileService;
	
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
	
	@GetMapping("/admin-course-category")
	public String adminCourseCategory(Model model)
	{
		model.addAttribute("content","admin/admin-course-category");
		return "/admin/admin-layout";
	}
	
	@GetMapping("/admin-student-list")
	public String adminStudentList(Model model)
	{
		List<User> users = userService.getAllStudents();
		model.addAttribute("users",users);
		model.addAttribute("content","admin/admin-student-list");
		return "/admin/admin-layout";
	}
	
	@PostMapping("/admin-student-list") 
	public String getEmployeeDataFromExcel(@RequestParam(name = "file",required = false)MultipartFile excelFile,Model model) throws IOException
	{
		model.addAttribute("content","admin/admin-student-list");
		String errorMessage;
		if(excelFile.isEmpty())
		{
			errorMessage = "File must not be empty!";
			model.addAttribute("errorMessage",errorMessage);
			return "/admin/admin-layout";
		}
		inputFileService.WriteEmployeeDataFromExcel(excelFile);
		return "/admin/admin-layout";
	}
	@GetMapping("/admin-student-list/search")
	public String adminStudentListSearch(@RequestParam("search")String name,Model model)
	{
		List<User> users = userService.searchUsersByName(name);
		model.addAttribute("users",users);
		model.addAttribute("content","admin/admin-student-list");
		return "/admin/admin-layout";
	}
	
	@GetMapping("/admin-instructor-list")
	public String adminInstructorList(Model model)
	{
		List<User> users = userService.getAllInstructors();
		model.addAttribute("users",users);
		model.addAttribute("content","admin/admin-instructor-list");
		return "/admin/admin-layout";
	}
	@GetMapping("/admin-instructor-request")
	public String adminInstructorRequest(Model model)
	{
		model.addAttribute("content","admin/admin-instructor-request");
		return "/admin/admin-layout";
	}
	@GetMapping("/admin-view-blog")
	public String adminViewBlog(Model model)
	{
		model.addAttribute("content","admin/admin-view-blog");
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
	@GetMapping("/forum")
	public String forum()
	{
		return "/authenticated-user/forum";
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
	@GetMapping("/admin-create-blog")
	public String adminCreateBlog(Model model) {
		return "/admin/admin-create-blog";
	}
	@GetMapping("/admin-create-course")
	public String adminCreateCourse(Model model) {
		return "/admin/admin-create-course";
	}
	@GetMapping("/admin-quiz")
	public String adminQuiz(Model model) {
		return "/admin/admin-quiz";
	}
	
	
}