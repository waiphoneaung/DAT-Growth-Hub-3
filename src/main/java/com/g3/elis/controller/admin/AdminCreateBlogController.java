package com.g3.elis.controller.admin;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.multipart.MultipartFile;

import com.g3.elis.dto.form.BlogPostDto;
import com.g3.elis.model.BlogPost;
import com.g3.elis.model.User;
import com.g3.elis.security.LoginUserDetail;
import com.g3.elis.service.BlogPostService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminCreateBlogController {

    @Autowired
    private BlogPostService blogPostService;

    @GetMapping("/admin-view-blog")
    public String adminViewBlog(Model model) {
        model.addAttribute("blogPosts", blogPostService.getAllBlogPosts());
        model.addAttribute("content", "admin/admin-view-blog");
        return "/admin/admin-layout";
    }

    @GetMapping("/blog-detail/{id}")
    public String adminViewBlogDetail(@PathVariable("id") int id, Model model) throws IOException {
        BlogPost blogPost = blogPostService.findById(id);
        if (blogPost != null) {
            model.addAttribute("htmlContent", blogPostService.getBlogPostContent(blogPost));
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

    @PostMapping("/admin-save-blog")
    public String adminCreateBlog(@Valid @ModelAttribute("blogPostDto") BlogPostDto blogPostDto,
                                  @RequestParam String content,
                                  @RequestParam(name = "img-file", required = false) MultipartFile imgFile,
                                  BindingResult result, Authentication authentication, Model model) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("blogPostDto", blogPostDto);
            return "/admin/admin-create-blog";
        }

        LoginUserDetail userDetail = (LoginUserDetail) authentication.getPrincipal();
        blogPostDto.setUsers(userDetail.getUser());

        blogPostService.saveBlogPost(blogPostDto, content, imgFile);

        return "redirect:/admin/admin-view-blog";
    }

    @GetMapping("/admin-delete-blog/{id}")
    public String adminDeleteBlog(@PathVariable int id) throws IOException {
    	blogPostService.deleteBlogPost(id);
        return "redirect:/admin/admin-view-blog";
}
    
}