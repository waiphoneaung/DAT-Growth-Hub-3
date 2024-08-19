package com.g3.elis.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.g3.elis.model.Forum;
import java.util.List;


@Repository
public interface ForumRepository extends JpaRepository<Forum, Integer>{
	
	List<Forum> findByTitleContainingIgnoreCase(String title);
	Page<Forum> findByTitleContainingIgnoreCase(String query, Pageable pageable);
   // List<Forum> findByUsername(String username);
//	Page<Forum> findByUserId(int userId, Pageable pageable);

}
