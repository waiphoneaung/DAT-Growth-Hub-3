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
	
	
	Page<BlogPost> getAllBlogPosts(Pageable pageable);

	Page<BlogPost> searchBlogPostsByTitle(String keyword, Pageable pageable);

	Page<BlogPost> getPaginatedBlogPosts(int page, int size);
    
  void saveBlogPost(BlogPostDto blogPostDto, String content, MultipartFile imgFile) throws IOException;
    
  void deleteBlogPost(int id) throws IOException;

void updateBlogPost( BlogPostDto blogPostDto, String content, MultipartFile imgFile) throws IOException;
    
public Page<BlogPost> searchBlogPostsByKeyword(String keyword, Pageable pageable);


}