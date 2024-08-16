package com.g3.elis.controller.admin;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.g3.elis.dto.form.ForumCommentDto;
import com.g3.elis.dto.form.ForumDto;
import com.g3.elis.model.Forum;
import com.g3.elis.model.User;
import com.g3.elis.repository.ForumRepository;
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
	public String viewMyPost(Authentication authentication, Model model) {
		
//		Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
//		Page<Forum> forumPage = forumService.getAllForums(pageable);
		List<User> users = userService.getAllUsers();
//		List<Forum> forums = forumService.getForumByUser(users);
		
		ForumDto forumDto = new ForumDto();
		ForumCommentDto forumCommentDto = new ForumCommentDto();


		model.addAttribute("users", users);
		model.addAttribute("forumCommentDto", forumCommentDto);
		model.addAttribute("forumDto", forumDto);
//		model.addAttribute("forums", forumPage.getContent());
//		model.addAttribute("currentPage", page);
//		model.addAttribute("totalPages", forumPage.getTotalPages());

		return "/authenticated-user/my-post";
	}

}
