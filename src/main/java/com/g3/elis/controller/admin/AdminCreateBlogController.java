package com.g3.elis.controller.admin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.g3.elis.config.FileStorageConfig;
import com.g3.elis.dto.form.BlogPostDto;
import com.g3.elis.model.BlogPost;
import com.g3.elis.model.User;
import com.g3.elis.security.LoginUserDetail;
import com.g3.elis.service.BlogPostService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminCreateBlogController {

	private final String imgUploadDir =  "/blog/blog-images/";
	public final String htmlUploadDir = "/blog/blog-files/";


	@Autowired
	private BlogPostService blogPostService;

	@GetMapping("/admin-view-blog")
	public String adminViewBlog(
	        @RequestParam(defaultValue = "0") int page, 
	        @RequestParam(required = false) String keyword, 
	        Model model) {

	    int pageSize = 8; // Set the page size to 8
	    Pageable pageable = PageRequest.of(page, pageSize);
	    
	    Page<BlogPost> blogPostsPage;
	    if (keyword != null && !keyword.isEmpty()) {
	        // If a keyword is provided, search for blog posts by the keyword
	        blogPostsPage = blogPostService.searchBlogPostsByKeyword(keyword, pageable);
	    } else {
	        // If no keyword is provided, fetch all blog posts
	        blogPostsPage = blogPostService.getAllBlogPosts(pageable);
	    }
	    
	    int previousPage = (page > 0) ? page - 1 : 0;
	    int nextPage = (page < blogPostsPage.getTotalPages() - 1) ? page + 1 : blogPostsPage.getTotalPages() - 1;

	    model.addAttribute("blogPosts", blogPostsPage.getContent());
	    model.addAttribute("currentPage", page);
	    model.addAttribute("nextPage", nextPage);
	    model.addAttribute("previousPage", previousPage);
	    model.addAttribute("totalPages", blogPostsPage.getTotalPages());
	    model.addAttribute("keyword", keyword); // To retain the keyword in the search field
	    
	    model.addAttribute("content", "admin/admin-view-blog");
	    return "/admin/admin-layout";
	}

	@SuppressWarnings("unused")
	@GetMapping("/blog-detail/{id}")
	public String adminViewBlogDetail(@PathVariable("id") int id, Model model) throws IOException {
		BlogPost blogPost = blogPostService.findById(id);
		if (blogPost != null) {
			LocalDateTime createdAt = blogPost.getCreatedAt().toLocalDateTime(); // If getCreatedAt() returns a
																					// Timestamp
			LocalDateTime now = LocalDateTime.now();
			Duration duration = Duration.between(createdAt, now);
			long days = duration.toDays();
			long hours = duration.toHours() % 24;
			long minutes = duration.toMinutes() % 60;
			String timeAgo = String.format("%d days, %d hours, %d minutes ago", days, hours, minutes);

			model.addAttribute("timeAgo", timeAgo);
			// Read HTML file content
			Path htmlFilePath = Paths.get(htmlUploadDir + blogPost.getHtmlFileName());
			model.addAttribute("blogPost", blogPost);
			return "/user/blog-detail";
		}

		return "redirect:/admin/admin-view-blog";
	}

	@GetMapping("/blog-html/{fileName}")
	@ResponseBody
	public String getBlogHtml(@PathVariable String fileName) throws IOException {
		Path htmlFilePath = Paths.get(htmlUploadDir + fileName);
		return Files.readString(htmlFilePath);
	}



	@GetMapping("/admin-delete-blog/{id}")
	public String adminDeleteBlog(@PathVariable int id) throws IOException {
		blogPostService.deleteBlogPost(id);
		return "redirect:/admin/admin-view-blog";
	}
	


    @GetMapping("/admin-create-blog")
    public String adminCreateBlog(Model model) {
        model.addAttribute("blogPostDto", new BlogPostDto());
        
        return "/admin/admin-create-blog";
    }

    @PostMapping("/admin-save-blog")
    public String adminSaveBlog(@Valid @ModelAttribute("blogPostDto") BlogPostDto blogPostDto,
                                @RequestParam String content,
                                @RequestParam(name = "img-file", required = false) MultipartFile imgFile,
                                BindingResult result, Authentication authentication, Model model) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("blogPostDto", blogPostDto);
           
            return "/admin/admin-create-blog";
        }

        LoginUserDetail userDetail = (LoginUserDetail) authentication.getPrincipal();
        blogPostDto.setUsers(userDetail.getUser());

       if (blogPostDto.getId() >0 && blogPostService.findById(blogPostDto.getId()) != null) {
           // If the ID exists, update the existing blog post
            blogPostService.updateBlogPost(blogPostDto, content, imgFile);
            System.out.println(content);
        } else {
          //   If the ID does not exist, create a new blog post
            blogPostService.saveBlogPost(blogPostDto, content, imgFile);
            System.out.println(content);
  
        }

        return "redirect:/admin/admin-view-blog";
    }
    
    

    @GetMapping("/admin-edit-blog/{id}")
    public String adminEditBlog(@Valid @PathVariable int id, String content, Model model) throws IOException {
        BlogPost blogPost = blogPostService.findById(id);
        if (blogPost == null) {
            return "redirect:/admin/admin-view-blog";
        }
        	
         BlogPostDto blogPostDto = new BlogPostDto();
        blogPostDto.setId(blogPost.getId());
        blogPostDto.setTitle(blogPost.getTitle());
        blogPostDto.setHtmlFileName(blogPost.getHtmlFileName());
        blogPostDto.setCreatedAt(blogPost.getCreatedAt());
        blogPostDto.setUsers(blogPost.getUsers());

        model.addAttribute("blogPostDto", blogPostDto);
        model.addAttribute("content", content );
        return "/admin/admin-edit-blog";
    }

}