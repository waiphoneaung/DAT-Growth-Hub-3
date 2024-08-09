package com.g3.elis.controller.admin;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.g3.elis.model.User;
import com.g3.elis.service.UserService;

@Controller
@RequestMapping("/admin")
public class InstructorListController {

    @Autowired
    UserService userService;
    
    @GetMapping("/admin-instructor-list")
	public String adminInstructorList(Model model)
	{
		List<User> users = userService.getAllInstructors();
	model.addAttribute("users",users);
	model.addAttribute("content","admin/admin-instructor-list");
	return "/admin/admin-layout";
	}

    @GetMapping("/admin-instructor-list/search")
    public String searchInstructors(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "staffId", required = false) String staffId,
            @RequestParam(value = "dept", required = false) String dept,
            @RequestParam(value = "division", required = false) String division,
            Model model) {

        List<User> users = userService.searchInstructors(name, staffId, dept, division);

        model.addAttribute("users", users);
        model.addAttribute("content", "admin/admin-instructor-list");
        return "/admin/admin-layout";
    }
    
    @PostMapping("/change-user-status")
    public String changeUserStatus(@RequestParam("id") int id, @RequestParam("enabled") boolean enabled) {
        userService.updateUserStatus(id, enabled);
        return "redirect:/admin/admin-instructor-list";
    }
}
