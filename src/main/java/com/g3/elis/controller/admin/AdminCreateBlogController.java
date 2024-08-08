package com.g3.elis.controller.admin;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.g3.elis.dto.form.BlogPostDto;
import com.g3.elis.model.BlogPost;
import com.g3.elis.service.BlogPostService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminCreateBlogController {

	@Autowired
	private BlogPostService blogPostService;

	@GetMapping("/admin-view-blog")
	public String adminViewBlog(Model model) {

		List<BlogPost> blogPosts = blogPostService.getAllBlogPosts();

		model.addAttribute("blogPosts", blogPosts);
		model.addAttribute("content", "admin/admin-view-blog");
		return "/admin/admin-layout";
	}

	@SuppressWarnings("unused")
	@GetMapping("/blog-detail/{id}")
	public String adminViewBlogDetail(@PathVariable("id") int id, Model model) {
		BlogPost blogPost = blogPostService.findById(id);

		// TIME DIFFERENT
		LocalDateTime createdAt = blogPost.getCreatedAt().toLocalDateTime(); // If getCreatedAt() returns a Timestamp
		LocalDateTime now = LocalDateTime.now();
		Duration duration = Duration.between(createdAt, now);
		long days = duration.toDays();
		long hours = duration.toHours() % 24;
		long minutes = duration.toMinutes() % 60;
		String timeAgo = String.format("%d days, %d hours, %d minutes ago", days, hours, minutes);

		model.addAttribute("timeAgo", timeAgo);

		// END TIME DIFFERENT

		if (blogPost != null) {

			model.addAttribute("blogPost", blogPost);
			return "/authenticated-user/blog-detail";
		}

		return "redirect:/admin/admin-view-blog";
	}

	@GetMapping("/admin-create-blog")
	public String adminCreateBlog(Model model) {

		model.addAttribute("blogPostDto", new BlogPostDto());
		return "/admin/admin-create-blog";
	}

	@PostMapping("/admin-create-blog")
	public String adminBlogCreate(@Valid @ModelAttribute("blogPostDto") BlogPostDto blogPostDto,
			@RequestParam(name = "img-file", required = false) MultipartFile imgFile, BindingResult result, Model model)
			throws IOException {

		if (result.hasErrors()) {
			model.addAttribute("blogPostDto", blogPostDto);
			return "/admin/admin-create-blog";
		}
		
		if (imgFile.isEmpty()) {
			result.addError(new FieldError("blogPostDto", "blogImage", "The image file is required"));
		}		
		
		blogPostDto.setImageFile(imgFile);
		blogPostDto.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		blogPostDto.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		blogPostService.saveBlogPost(blogPostDto);

		return "redirect:/admin/admin-view-blog";
	}
	
	@GetMapping("/admin-delete-blog/{id}")
	public String adminDeleteBlog(@PathVariable int id) throws IOException {
		
		blogPostService.deleteBlogPost(id);
		return "redirect:/admin/admin-view-blog";
	}

}
