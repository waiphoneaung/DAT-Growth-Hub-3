package com.g3.elis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.g3.elis.model.ForumComment;

@Repository
public interface ForumCommentRepository extends JpaRepository<ForumComment, Integer>{

}
