package com.g3.elis.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g3.elis.dto.form.ForumCommentDto;
import com.g3.elis.model.Forum;
import com.g3.elis.model.ForumComment;
import com.g3.elis.repository.ForumCommentRepository;
import com.g3.elis.repository.ForumRepository;
import com.g3.elis.service.ForumCommentService;

@Service
public class ForumCommentServiceImpl implements ForumCommentService{
	
	@Autowired
	ForumCommentRepository forumCommentRepository;
	
	@Autowired
	ForumRepository forumRepository;
	
	@Override
	public List<ForumComment> getAllComments() {
		// TODO Auto-generated method stub
		return forumCommentRepository.findAll();
	}

	
	@Override
	public void saveComment(ForumCommentDto forumCommentDto) {

		ForumComment forumComment = new ForumComment();
		
		forumComment.setUsers(forumCommentDto.getUsers());
		forumComment.setCreatedAt(forumCommentDto.getCreatedAt());
		forumComment.setComment(forumCommentDto.getComment());
//		Forum forum = forumRepository.findById(forumCommentDto.getForums().getId())
//			    .orElseThrow(() -> new RuntimeException("Forum not found with id: " + forumCommentDto.getForums().getId()));

//		forumComment.setForums(forum);
		
		forumCommentRepository.save(forumComment);
		
	}




	

}
