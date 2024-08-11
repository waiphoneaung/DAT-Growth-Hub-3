package com.g3.elis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.g3.elis.model.Profile;
import com.g3.elis.model.User;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
	 @Query("SELECT p FROM Profile p WHERE p.user = :user")
	 Profile findProfileByUser(@Param("user") User user);
}
