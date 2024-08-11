package com.g3.elis.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.g3.elis.dto.form.BlogPostDto;
import com.g3.elis.model.BlogPost;

public interface BlogPostService {
    
    List<BlogPost> getAllBlogPosts();
    
    BlogPost findById(int id);
    
    void saveBlogPost(BlogPostDto blogPostDto, String content, MultipartFile imgFile) throws IOException;
    
    void deleteBlogPost(int id) throws IOException;
    
    void updateBlogPost(BlogPostDto blogPostDto, String content, MultipartFile imgFile) throws IOException;
    
    String getBlogPostContent(BlogPost blogPost) throws IOException;
}
