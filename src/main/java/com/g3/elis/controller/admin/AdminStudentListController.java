package com.g3.elis.controller.admin;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.g3.elis.model.Role;
import com.g3.elis.model.User;
import com.g3.elis.service.RoleService;
import com.g3.elis.service.UserService;
import com.g3.elis.util.InputFileService;

@Controller
@RequestMapping("/admin")
public class AdminStudentListController 
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private InputFileService inputFileService;
	
	
	@GetMapping("/admin-student-list")
    public String adminStudentList(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "9") int size,
            Model model)
    {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<User> userPage = userService.getAllStudents(pageable);
        
        model.addAttribute("users", userPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", userPage.getTotalPages());
        model.addAttribute("content", "admin/admin-student-list");
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
		return "redirect:/admin/admin-dashboard";
	}
	
	@GetMapping("/admin-student-list/search")
	public String adminStudentListSearch(
	        @RequestParam("search") String name,
	        @RequestParam(value = "page", defaultValue = "0") int page,
	        @RequestParam(value = "size", defaultValue = "10") int size,
	        Model model) {

	    Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
	    Page<User> userPage = userService.searchUsersByName(name, pageable);

	    model.addAttribute("users", userPage.getContent());
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", userPage.getTotalPages());
	    model.addAttribute("search", name); // Pass the search term to the view
	    model.addAttribute("size", size);   // Pass the page size to the view
	    model.addAttribute("content", "admin/admin-student-list");
	    return "/admin/admin-layout";
	}

	
	@GetMapping("/change-student-status")
	public String changeStudentStatus(@RequestParam("id") int id, @RequestParam("enabled") boolean enabled) {
	    userService.updateUserStatus(id, enabled);
	    return "redirect:/admin/admin-student-list";
	}
	
	@GetMapping("/change-role-student")
    public String changeUserRole(@RequestParam("userId") int userId, @RequestParam("roleName") String name) {
        User user = userService.findById(userId);
        Optional<Role> role = roleService.findByName(name);

        if (user != null && role.isPresent()) {
            user.getRoles().clear(); 
            user.getRoles().add(role.get());

            role.get().getUsers().add(user); 

            userService.save(user);
        }


        return "redirect:/admin/admin-student-list";
    }

}
