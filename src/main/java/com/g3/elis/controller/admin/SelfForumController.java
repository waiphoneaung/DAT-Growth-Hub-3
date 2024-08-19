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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.g3.elis.dto.form.ForumCommentDto;
import com.g3.elis.dto.form.ForumDto;
import com.g3.elis.model.Forum;
import com.g3.elis.model.User;
import com.g3.elis.repository.ForumRepository;
import com.g3.elis.security.LoginUserDetail;
import com.g3.elis.service.ForumCommentService;
import com.g3.elis.service.ForumService;
import com.g3.elis.service.UserService;

@Controller
@RequestMapping({ "/admin", "/student", "/instructor" })
public class SelfForumController {

	@Autowired
	private ForumService forumService;

	@Autowired
	private ForumCommentService forumCommentService;

	@Autowired
	private UserService userService;

	@GetMapping("/my-post")
	public String viewMyPost(Model model, Authentication authentication) {

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

	//	List<Forum> forums = forumService.getAllForums();
		List<Forum> forums = forumService.getAllForumsSortedByCreatedAtDesc();
		List<User> users = userService.getAllUsers();

		ForumDto forumDto = new ForumDto();
		ForumCommentDto forumCommentDto = new ForumCommentDto();

//		model.addAttribute("users", users);
		model.addAttribute("forumCommentDto", forumCommentDto);
		model.addAttribute("forumDto", forumDto);
		model.addAttribute("forums", forums);
		//model.addAttribute("currentPage", page);
		//model.addAttribute("totalPages", forumPage.getTotalPages());

		return "/authenticated-user/my-post";
	}

	@PostMapping("/my-post")
	public String CreateForum(@ModelAttribute("forumDto") ForumDto forumDto, Authentication authentication) {

		LoginUserDetail userDetail = (LoginUserDetail) authentication.getPrincipal();
		User user = userDetail.getUser();
		forumDto.setUsers(user);

		forumDto.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		forumService.saveForum(forumDto);

		// Redirect based on user role
		if (userDetail.isAdmin()) {
			return "redirect:/admin/my-post";
		} else if (userDetail.isStudent()) {
			return "redirect:/student/my-post";
		} else if (userDetail.isInstructor()) {
			return "redirect:/instructor/my-post";
		} else {
			return "redirect:/login";
		}
	}
	
	@PostMapping("/self-comment")
	public String adminCreateComment(@ModelAttribute("forumCommentDto") ForumCommentDto forumCommentDto,
			@RequestParam("forumId") int forumId, Authentication authentication) {

		LoginUserDetail userDetail = (LoginUserDetail) authentication.getPrincipal();
		User user = userDetail.getUser();

		forumCommentDto.setUsers(user);
		//forumCommentDto.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		forumCommentService.saveComment(forumCommentDto, forumId);
		//return "redirect:/admin/forum";
		
		if (userDetail.isAdmin()) {
			return "redirect:/admin/my-post";
		} else if (userDetail.isStudent()) {
			return "redirect:/student/my-post";
		} else if (userDetail.isInstructor()) {
			return "redirect:/instructor/my-post";
		} else {
			return "redirect:/login";
		}
	}
	
	/*
	 * @GetMapping("/my-post/edit") public String getPost(@RequestParam("id") int
	 * id, Model model, Authentication authentication) {
	 * 
	 * Forum forum = forumService.getForumById(id); ForumDto forumDto = new
	 * ForumDto(); forumDto.setTitle(forum.getTitle());
	 * forumDto.setContent(forum.getContent()); model.addAttribute("forumDto",
	 * forumDto);
	 * 
	 * return "/authenticated-user/my-post";
	 * 
	 * }
	 */	
	@PostMapping("/my-post/delete/{id}")
	public String deleteMyPost(@PathVariable("id") int id, Authentication authentication, Model model) {
			
		LoginUserDetail userDetail = (LoginUserDetail) authentication.getPrincipal();
		//User user = userDetail.getUser();
		

		
		forumService.deletePost(id);
		if (userDetail.isAdmin()) {
			return "redirect:/admin/my-post";
		} else if (userDetail.isStudent()) {
			return "redirect:/student/my-post";
		} else if (userDetail.isInstructor()) {
			return "redirect:/instructor/my-post";
		} else {
			return "redirect:/login";
		}
	}
}
