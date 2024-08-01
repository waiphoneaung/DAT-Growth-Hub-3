package com.g3.elis.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController{
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
		model.addAttribute("content","admin/admin-student-list");
		return "/admin/admin-layout";
	}
	@GetMapping("/admin-instructor-list")
	public String adminInstructorList(Model model)
	{
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