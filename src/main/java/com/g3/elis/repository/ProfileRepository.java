package com.g3.elis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.g3.elis.model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {

	Object findByUserId(Long userId);
	

}
