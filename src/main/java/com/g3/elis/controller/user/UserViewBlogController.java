package com.g3.elis.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.g3.elis.model.BlogPost;
import com.g3.elis.service.BlogPostService;

@Controller
@RequestMapping({"/user","/"})
public class UserViewBlogController {
	
	private final String HTML_FILE_PATH = "src/main/resources/static/private/blog/blog-files/";
    private final String IMAGE_FILE_PATH = "src/main/resources/static/private/blog/blog-images/";

	@Autowired
	private BlogPostService blogPostService;

	@GetMapping("/blog")
	public String adminViewBlog(Model model) {
		List<BlogPost> blogPosts = blogPostService.getAllBlogPosts();
		model.addAttribute("blogPosts", blogPosts);
		model.addAttribute("content", "user/blog");
		return "/user/layout";
	}
}

