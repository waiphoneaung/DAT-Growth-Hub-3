package com.g3.elis.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.g3.elis.model.BlogPost;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Integer>{

//	static List<BlogPost> findByRole(String string, Pageable pageable) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	static Page<BlogPost> findByNameContainingIgnoreCaseAndRole(String name, String string, Pageable pageable) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	static Page<BlogPost> searchInstructors(String name, String staffId, String dept, String division,
//			Pageable pageable) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
//    Page<BlogPost> getAllBlogPosts(Pageable pageable);

	Page<BlogPost> findAll(Pageable pageable);

	Page<BlogPost> findByTitleContainingIgnoreCase(String title, Pageable pageable);

	
	}


