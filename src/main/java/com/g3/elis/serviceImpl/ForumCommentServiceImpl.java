package com.g3.elis.serviceImpl;

import java.sql.Timestamp;
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
	public void saveComment(ForumCommentDto forumCommentDto,int forumId) {

		ForumComment forumComment = new ForumComment();
		Forum forum = forumRepository.findById(forumId).orElse(null);
		
		
		forumComment.setUsers(forumCommentDto.getUsers());
		forumComment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		forumComment.setComment(forumCommentDto.getComment());
		forumComment.setForums(forum);
		
//		Forum forum = forumRepository.findById(forumCommentDto.getForums().getId())
//			    .orElseThrow(() -> new RuntimeException("Forum not found with id: " + forumCommentDto.getForums().getId()));

//		forumComment.setForums(forum);
		
		forumCommentRepository.save(forumComment);
		
	}




	

}
