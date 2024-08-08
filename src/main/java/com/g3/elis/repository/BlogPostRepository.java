package com.g3.elis.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.g3.elis.model.BlogPost;
import com.g3.elis.model.User;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Integer>{


}
