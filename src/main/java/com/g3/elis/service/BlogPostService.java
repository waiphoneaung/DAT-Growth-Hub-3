package com.g3.elis.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.g3.elis.dto.form.BlogPostDto;
import com.g3.elis.model.BlogPost;
import com.g3.elis.model.User;

import jakarta.validation.Valid;


public interface BlogPostService {

	 List<BlogPost> getAllBlogPosts();

	 void saveBlogPost(@Valid BlogPostDto blogPostDto) throws IOException;

	 BlogPost findById(int id);


	void deleteBlogPost(int id) throws IOException;
	
	void updateBlogPost(BlogPostDto blogPostDto) throws IOException; // Method for updating a blog post
	
	
	public void updateBlogPostStatus(int id, boolean enabled);
	
	
//	 Page<BlogPost> getAllBlogs(Pageable pageable);
//	 Page<BlogPost> searchBlogPostsByName(String name, Pageable pageable);

	//List<BlogPost> getAllBlogPosts(Pageable pageable);
	public Page<BlogPost> getAllBlogPosts(Pageable pageable);

	Page<BlogPost> searchBlogPostsByTitle(String keyword, Pageable pageable);
	 

	

	
	
	
	
}


