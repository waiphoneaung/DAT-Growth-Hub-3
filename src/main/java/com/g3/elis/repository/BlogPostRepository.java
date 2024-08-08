package com.g3.elis.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.g3.elis.model.BlogPost;
import com.g3.elis.model.User;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Integer>{

//	public List<BlogPost> findAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public BlogPost save(BlogPost blogPost) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public Optional<User> findById(int id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public void delete(BlogPost blogPost) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public boolean existsById(int id) {
//		// TODO Auto-generated method stub
//		return false;
//	}

}
