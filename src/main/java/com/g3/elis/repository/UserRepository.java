package com.g3.elis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g3.elis.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {
	public String findByStaffId(String search);
}
