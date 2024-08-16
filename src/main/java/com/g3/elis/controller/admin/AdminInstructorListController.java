package com.g3.elis.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
public class AdminInstructorListController {

	@Autowired
	private UserService userService;

	@GetMapping("/admin-instructor-list")
    public String adminInstructorList(Model model, 
                                      @RequestParam(value = "page", defaultValue = "0") int page,
                                      @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<User> userPage = userService.getAllInstructors(pageable);

        model.addAttribute("users", userPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", userPage.getTotalPages());
        model.addAttribute("content", "admin/admin-instructor-list");
        return "/admin/admin-layout";
    }

    @GetMapping("/admin-instructor-list/search")
    public String searchInstructors(@RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "staffId", required = false) String staffId,
                                    @RequestParam(value = "dept", required = false) String dept,
                                    @RequestParam(value = "division", required = false) String division,
                                    @RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "size", defaultValue = "10") int size,
                                    Model model) {

        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = userService.searchInstructors(name, staffId, dept, division, pageable);

        model.addAttribute("users", userPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", userPage.getTotalPages());
        model.addAttribute("content", "admin/admin-instructor-list");
        return "/admin/admin-layout";
    }
	@PostMapping("/change-instructor-status")
	public String changeInstructorStatus(@RequestParam("id") int id, @RequestParam("enabled") boolean enabled) {
		userService.updateUserStatus(id, enabled);
		return "redirect:/admin/admin-instructor-list";
	}
}
