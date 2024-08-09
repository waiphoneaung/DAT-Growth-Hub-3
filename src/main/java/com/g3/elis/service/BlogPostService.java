package com.g3.elis.service;

import java.io.IOException;
import java.util.List;

import com.g3.elis.dto.form.BlogPostDto;
import com.g3.elis.model.BlogPost;


public interface BlogPostService {

	 List<BlogPost> getAllBlogPosts();

	 void saveBlogPost(BlogPostDto blogPostDto) throws IOException;

	 BlogPost findById(int id);


	void deleteBlogPost(int id) throws IOException;
	
	void updateBlogPost(BlogPostDto blogPostDto) throws IOException; // Method for updating a blog post

}
