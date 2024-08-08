package com.g3.elis.service;

import java.io.IOException;
import java.util.List;

import com.g3.elis.dto.form.BlogPostDto;
import com.g3.elis.model.BlogPost;


public interface BlogPostService {

	public List<BlogPost> getAllBlogPosts();

	public void saveBlogPost(BlogPostDto blogPostDto) throws IOException;

	public BlogPost findById(int id);

//	public void deleteBlog(int id) throws IOException;

	void deleteBlogPost(int id) throws IOException;

}
