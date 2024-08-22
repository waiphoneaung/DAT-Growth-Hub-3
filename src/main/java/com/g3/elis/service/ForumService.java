package com.g3.elis.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.g3.elis.dto.form.ForumDto;
import com.g3.elis.model.Forum;

import jakarta.validation.Valid;

public interface ForumService {

	void saveForum(@Valid ForumDto forumDto);

	Forum findById(int forumId);

	List<Forum> getAllForums();

	List<Forum> searchPosts(String query);

	Page<Forum> getAllForums(Pageable pageable);

//	  getAllForums();
	Page<Forum> searchPosts(String query, int page,int pagesize);

	List<Forum> getAllForumsSortedByCreatedAtDesc();

	void deletePost(int id);

	Forum getForumById(int id);

	
//	List<Forum> getForumByUser(int userId);
	
}
