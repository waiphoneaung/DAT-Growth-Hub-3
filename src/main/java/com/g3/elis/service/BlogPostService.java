package com.g3.elis.service;

import java.io.IOException;
import java.util.List;

import com.g3.elis.dto.form.BlogPostDto;
import com.g3.elis.model.BlogPost;

import jakarta.validation.Valid;

public interface BlogPostService {

	 List<BlogPost> getAllBlogPosts();

	 void saveBlogPost(@Valid BlogPostDto blogPostDto) throws IOException;

	 BlogPost findById(int id);


	void deleteBlogPost(int id) throws IOException;

}
