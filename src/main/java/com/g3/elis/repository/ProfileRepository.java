package com.g3.elis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g3.elis.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {

}
