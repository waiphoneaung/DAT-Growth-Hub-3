package com.g3.elis.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.g3.elis.model.BlogPost;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Integer>{


	Page<BlogPost> findAll(Pageable pageable);

	Page<BlogPost> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);
	


	
	}


