package com.g3.elis.service;

import java.util.List;

import com.g3.elis.dto.form.ForumCommentDto;
import com.g3.elis.model.Forum;
import com.g3.elis.model.ForumComment;

public interface ForumCommentService {

	void saveComment(ForumCommentDto forumCommentDto,int forumId);

	List<ForumComment> getAllComments();



}
