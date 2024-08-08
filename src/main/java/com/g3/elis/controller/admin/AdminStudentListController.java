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
public class AdminStudentListController 
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private InputFileService inputFileService;
	
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

}
