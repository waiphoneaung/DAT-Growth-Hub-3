package com.g3.elis.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
//	@Override
//	public List<Forum> getAllForums() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public void saveForum(@Valid ForumDto forumDto) {

		Forum forum = new Forum();
		forum.setTitle(forumDto.getTitle());
		forum.setContent(forumDto.getContent());
		forumRepository.save(forum);
	}

}
