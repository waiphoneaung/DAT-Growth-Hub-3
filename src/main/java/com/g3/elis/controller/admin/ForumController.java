package com.g3.elis.controller.admin;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.g3.elis.dto.form.ForumCommentDto;
import com.g3.elis.dto.form.ForumDto;
import com.g3.elis.model.Forum;
import com.g3.elis.model.User;
import com.g3.elis.security.LoginUserDetail;
import com.g3.elis.service.ForumCommentService;
import com.g3.elis.service.ForumService;
import com.g3.elis.service.UserService;

@Controller
@RequestMapping({ "/admin", "/student", "/instructor" })
public class ForumController {

	@Autowired
	private ForumService forumService;

	@Autowired
	private ForumCommentService forumCommentService;

	@Autowired
	private UserService userService;
	
	@GetMapping("/forum")
	public String adminForum(@RequestParam(value = "page", defaultValue = "0") int page,
							 @RequestParam(value = "size", defaultValue = "8") int size, Model model, 
							 Authentication authentication) 
	{
		LoginUserDetail userDetail = (LoginUserDetail) authentication.getPrincipal();
		if (userDetail.isAdmin()) {
			String mapping = "/admin";
			model.addAttribute("map", mapping);
		}
		if (userDetail.isStudent()) {
			String mapping = "/student";
			model.addAttribute("map", mapping);
		}
		if (userDetail.isInstructor()) {
			String mapping = "/instructor";
			model.addAttribute("map", mapping);
		}
		
		Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
		Page<Forum> forumPage = forumService.getAllForums(pageable);
		List<User> users = userService.getAllUsers();
	
		ForumDto forumDto = new ForumDto();
		ForumCommentDto forumCommentDto = new ForumCommentDto();


//		model.addAttribute("users", users);
		model.addAttribute("forumCommentDto", forumCommentDto);
		model.addAttribute("forumDto", forumDto);
		model.addAttribute("forums", forumPage.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", forumPage.getTotalPages());

		return "authenticated-user/forum";
	}

	// search for post
	@GetMapping("/forum/search")
	public String search(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "8") int size, @RequestParam("query") String query,
			Authentication authentication, Model model) {

		LoginUserDetail userDetail = (LoginUserDetail) authentication.getPrincipal();
		if (userDetail.isAdmin()) {
			String mapping = "/admin";
			model.addAttribute("map", mapping);
		}
		if (userDetail.isStudent()) {
			String mapping = "/student";
			model.addAttribute("map", mapping);
		}
		if (userDetail.isInstructor()) {
			String mapping = "/instructor";
			model.addAttribute("map", mapping);
		}

		ForumDto forumDto = new ForumDto();
		// List<Forum> title = forumService.searchPosts(query);
		ForumCommentDto forumCommentDto = new ForumCommentDto();

		Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
		Page<Forum> title = forumService.searchPosts(query, page, size);

		// Page<Forum> forumPage = forumService.getAllForums(pageable);
		model.addAttribute("forums", title.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", title.getTotalPages());

		model.addAttribute("forumDto", forumDto);
		model.addAttribute("forumCommentDto", forumCommentDto);
		model.addAttribute("forums", title);
		return "authenticated-user/forum";
	}

	@PostMapping("/forum")
	public String adminCreateForum(@ModelAttribute("forumDto") ForumDto forumDto, Authentication authentication) {

		LoginUserDetail userDetail = (LoginUserDetail) authentication.getPrincipal();
		User user = userDetail.getUser();
		forumDto.setUsers(user);

		forumDto.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		forumService.saveForum(forumDto);

		// Redirect based on user role
		if (userDetail.isAdmin()) {
			return "redirect:/admin/forum";
		} else if (userDetail.isStudent()) {
			return "redirect:/student/forum";
		} else if (userDetail.isInstructor()) {
			return "redirect:/instructor/forum";
		} else {
			return "redirect:/login";
		}
	}

	@PostMapping("/comment")
	public String adminCreateComment(@ModelAttribute("forumCommentDto") ForumCommentDto forumCommentDto,
			@RequestParam("forumId") int forumId, Authentication authentication) {

		LoginUserDetail userDetail = (LoginUserDetail) authentication.getPrincipal();
		User user = userDetail.getUser();

		forumCommentDto.setUsers(user);
		//forumCommentDto.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		forumCommentService.saveComment(forumCommentDto, forumId);
		//return "redirect:/admin/forum";
		
		if (userDetail.isAdmin()) {
			return "redirect:/admin/forum";
		} else if (userDetail.isStudent()) {
			return "redirect:/student/forum";
		} else if (userDetail.isInstructor()) {
			return "redirect:/instructor/forum";
		} else {
			return "redirect:/login";
		}
	}
	
	}
