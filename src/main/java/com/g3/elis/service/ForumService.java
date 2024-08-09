package com.g3.elis.service;

import java.util.List;

import com.g3.elis.dto.form.ForumDto;
import com.g3.elis.model.Forum;

import jakarta.validation.Valid;

public interface ForumService {

	void saveForum(@Valid ForumDto forumDto);

//	  getAllForums();

}
