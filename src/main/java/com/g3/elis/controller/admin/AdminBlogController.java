//package com.g3.elis.controller.admin;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.g3.elis.dto.form.BlogPostDto;
//import com.g3.elis.model.BlogPost;
//import com.g3.elis.service.BlogPostService;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
//@Controller
//@Validated
//public class AdminBlogController {
//
//
//	
//	    @Value("${blog.htmlUploadDir}")
//	    private String htmlUploadDir;
//
//	    private final BlogPostService blogPostService;
//
//	    public AdminBlogController(BlogPostService blogPostService) {
//	        this.blogPostService = blogPostService;
//	    }
//
//	    @GetMapping("/admin/admin-edit-blog/{id}")
//	    public String adminEditBlog(@PathVariable int id, Model model) {
//	        BlogPost blogPost = blogPostService.findById(id);
//	        if (blogPost == null) {
//	            return "admin-edit-blog";
//	        }
//
//	        String content;
//	        try {
//	            content = Files.readString(Paths.get(htmlUploadDir, blogPost.getHtmlFileName()));
//	        } catch (IOException e) {
//	            content = "Error reading content";
//	            // Consider logging the exception
//	        }
//
//	        BlogPostDto blogPostDto = new BlogPostDto();
//	        blogPostDto.setId(blogPost.getId());
//	        blogPostDto.setTitle(blogPost.getTitle());
//	        blogPostDto.setHtmlFileName(blogPost.getHtmlFileName());
//	        blogPostDto.setCreatedAt(blogPost.getCreatedAt());
//	        blogPostDto.setUsers(blogPost.getUsers());
//
//	        model.addAttribute("blogPostDto", blogPostDto);
//	        model.addAttribute("content", content);
//
//	        return "admin/admin-edit-blog";
//	    }
//
//	    @PostMapping("/admin-edit-blog/{id}")
//	    public String updateBlogPost(@PathVariable int id,
//	                                 @RequestParam String content,
//	                                 RedirectAttributes redirectAttributes) {
//	        try {
//	            BlogPost blogPost = blogPostService.findById(id);
//	            if (blogPost == null) {
//	                redirectAttributes.addFlashAttribute("error", "Blog post not found");
//	                return "redirect:/admin/admin-view-blog";
//	            }
//
//	            // Save the updated content to the file
//	            Files.write(Paths.get(htmlUploadDir, blogPost.getHtmlFileName()), content.getBytes());
//
//	            // Optionally update other fields of the blog post if needed
//	            // blogPost.setContent(content); // example
//
//	            blogPostService.save(blogPost); // Save any changes to the blog post entity
//
//	            redirectAttributes.addFlashAttribute("success", "Blog post updated successfully");
//	            return "redirect:/admin/admin-view-blog";
//	        } catch (IOException e) {
//	            redirectAttributes.addFlashAttribute("error", "Error updating blog post");
//	            // Consider logging the exception
//	            return "redirect:/admin/admin-edit-blog/" + id;
//	        }
//	    }
//	}
//
//
//
