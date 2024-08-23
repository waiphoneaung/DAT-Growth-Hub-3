package com.g3.elis.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.g3.elis.dto.form.BlogPostDto;
import com.g3.elis.model.BlogPost;

import jakarta.validation.Valid;


public interface BlogPostService {

	 List<BlogPost> getAllBlogPosts();

	//void saveBlogPost(@Valid BlogPostDto blogPostDto) throws IOException;

	 BlogPost findById(int id);
	
	//void updateBlogPost(BlogPostDto blogPostDto) throws IOException; // Method for updating a blog post
	
	
//	void updateBlogPostStatus(int id, boolean enabled);
	
 //Page<BlogPost> getAllBlogs(Pageable pageable);
	// Page<BlogPost> searchBlogPostsByName(String name, Pageable pageable);

	
	//List<BlogPost> getAllBlogPosts(Pageable pageable);
	Page<BlogPost> getAllBlogPosts(Pageable pageable);

	Page<BlogPost> searchBlogPostsByTitle(String keyword, Pageable pageable);

	Page<BlogPost> getPaginatedBlogPosts(int page, int size);
    
  void saveBlogPost(BlogPostDto blogPostDto, String content, MultipartFile imgFile) throws IOException;
    
  void deleteBlogPost(int id) throws IOException;

void updateBlogPost( BlogPostDto blogPostDto, String content, MultipartFile imgFile) throws IOException;
    
//  void updateBlogPost(BlogPostDto blogPostDto, String content, MultipartFile imgFile) throws IOException;
    
 // String getBlogPostContent(BlogPost blogPost) throws IOException;
}