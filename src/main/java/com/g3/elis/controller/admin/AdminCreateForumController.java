package com.g3.elis.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.g3.elis.dto.form.ForumDto;
import com.g3.elis.service.ForumService;

@Controller
@RequestMapping("/admin")
public class AdminCreateForumController {
	
	@Autowired
	private ForumService forumService;
	
	@GetMapping("/forum")
	public String instructorForum(Model model) {
		  ForumDto forumDto = new ForumDto();
		    model.addAttribute("forumDto", forumDto);
//		List<Forum> forums = forumService.getAllForums();
//		model.addAttribute("forums", forums);
		return "authenticated-user/forum";
	}
	
//	@GetMapping("/create-forum")
//	public String adminViewForum(Model model) {
//			
//		ForumDto forumDto = new ForumDto();
//		model.addAttribute("forumDto", forumDto);
//	    System.out.println("ForumDto object added to model: " + forumDto);
//		return "authenticated-user/forum";
//	}
	
//	@PostMapping("/create-forum")
//	public String adminCreateForum(@Valid @ModelAttribute("forumDto") ForumDto forumDto) {
//		
//		
//		forumDto.setCreatedAt(new Timestamp(System.currentTimeMillis()));
//		forumService.saveForum(forumDto);
//		return "redirect:/admin/forum";
//	}
	
	

	@PostMapping("/createForum")
	public String createForum(@ModelAttribute ForumDto forumDto, RedirectAttributes redirectAttributes) {
		 
	    // Handle form submission logic here
	    redirectAttributes.addFlashAttribute("message", "Forum created successfully!");
	    return "redirect:/admin/forumList"; // Redirect to a page that lists forums or similar
	}

}
