package com.g3.elis.controller.admin;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.g3.elis.dto.form.ForumCommentDto;
import com.g3.elis.dto.form.ForumDto;
import com.g3.elis.model.Forum;
import com.g3.elis.model.ForumComment;
import com.g3.elis.model.User;
import com.g3.elis.security.LoginUserDetail;
import com.g3.elis.service.ForumCommentService;
import com.g3.elis.service.ForumService;

@Controller
@RequestMapping("/admin")
public class AdminCreateForumController {

	@Autowired
	private ForumService forumService;
	@Autowired
	private ForumCommentService forumCommentService;

	@GetMapping("/forum")
	public String adminForum(Model model) {
		ForumDto forumDto = new ForumDto();
		ForumCommentDto forumCommentDto = new ForumCommentDto();
		List<Forum> forums = forumService.getAllForums();
		List<ForumComment> forumComments = forumCommentService.getAllComments();
		model.addAttribute("forumCommentDto", forumCommentDto);
		model.addAttribute("forumDto", forumDto);
		model.addAttribute("forums", forums);
		model.addAttribute("forumComments", forumComments);
		return "authenticated-user/forum";
	}

//    @GetMapping("/forum/{id}/comment")
//    public String showCommentForm(@PathVariable("id") int forumId, Model model) {
//        Forum forum = forumService.findById(forumId);
//        ForumCommentDto forumCommentDto = new ForumCommentDto();
//        forumCommentDto.setForums(forum);  // Set Forum object in DTO
//        model.addAttribute("forumCommentDto", forumCommentDto);
//        model.addAttribute("forum", forum);
//        return "commentForm";  // Thymeleaf template name
//    }

	@PostMapping("/forum")
	public String adminCreateForum(@ModelAttribute("forumDto") ForumDto forumDto, Authentication authentication) {

		LoginUserDetail userDetail = (LoginUserDetail) authentication.getPrincipal();
		User user = userDetail.getUser();
		forumDto.setUsers(user);
		forumDto.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		forumService.saveForum(forumDto);
		return "redirect:/admin/forum";
	}

	@PostMapping("/comment")
	public String adminCreateComment(@ModelAttribute("forumCommentDto") ForumCommentDto forumCommentDto,
			Authentication authentication) {

		LoginUserDetail userDetail = (LoginUserDetail) authentication.getPrincipal();
		User user = userDetail.getUser();
		forumCommentDto.setUsers(user);
		forumCommentDto.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		
		forumCommentService.saveComment(forumCommentDto);
		return "redirect:/admin/forum";
	}
}
