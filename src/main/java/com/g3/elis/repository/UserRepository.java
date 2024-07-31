package com.g3.elis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.g3.elis.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
	public Optional<User> findByStaffId(String search);

}
