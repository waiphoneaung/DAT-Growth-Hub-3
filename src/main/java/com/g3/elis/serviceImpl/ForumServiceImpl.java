package com.g3.elis.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.g3.elis.dto.form.ForumDto;
import com.g3.elis.model.Forum;
import com.g3.elis.repository.ForumRepository;
import com.g3.elis.service.ForumService;

import jakarta.validation.Valid;

@Service
public class ForumServiceImpl implements ForumService{

	@Autowired
	ForumRepository forumRepository;
	@Override
	public List<Forum> getAllForums() {
		// TODO Auto-generated method stub
		return forumRepository.findAll();
	}

	@Override
	public void saveForum(@Valid ForumDto forumDto) {

		Forum forum = new Forum();
		forum.setUsers(forumDto.getUsers());
		forum.setCreatedAt(forumDto.getCreatedAt());
		forum.setTitle(forumDto.getTitle());
		forum.setContent(forumDto.getContent());
		forumRepository.save(forum);
	}

	@Override
	public Forum findById(int forumId) {
		// TODO Auto-generated method stub
		return forumRepository.findById(forumId).orElse(null);
	}
	
	
	//for searchPost
	@Override
	public List<Forum> searchPosts(String query) {
		// TODO Auto-generated method stub
		return forumRepository.findByTitleContainingIgnoreCase(query);
	}
	
	//test search pagination
//	public Page<Forum> searchPosts(String query, Pageable pageable) {
//        // Ensure the query is not null or empty
//        if (query == null || query.trim().isEmpty()) {
//            return forumRepository.findAll(pageable); // Return all posts with pagination if no query is provided
//        }
//        return forumRepository.findByTitleContainingIgnoreCase(query, pageable);
//    }

	@Override
	public Page<Forum> getAllForums(Pageable pageable) {
		// TODO Auto-generated method stub
    return forumRepository.findAll(pageable);

	}

	@Override
	public Page<Forum> searchPosts(String query, int page, int pagesize) {
		Pageable pageable=PageRequest.of(page, pagesize);
		if (query == null || query.trim().isEmpty()) {
         return forumRepository.findAll(pageable) ;// Return all posts with pagination if no query is provided
      }
     return forumRepository.findByTitleContainingIgnoreCase(query,pageable);	}

	@Override
	public List<Forum> getAllForumsSortedByCreatedAtDesc() {
		// TODO Auto-generated method stub
		return forumRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
	}

	@Override
	public void deletePost(int id) {
		// TODO Auto-generated method stub
	 forumRepository.deleteById(id);
	}

	@Override
	public Forum getForumById(int id) {
		return forumRepository.findById(id).orElse(null);
	}

	
//	@Override
//	public List<Forum> getForumByUser(int userId) {
//		
//		return forumRepository.findByUserId(userId);
//	}

}
