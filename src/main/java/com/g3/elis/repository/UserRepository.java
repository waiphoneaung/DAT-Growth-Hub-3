package com.g3.elis.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.g3.elis.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

	User findByEmail(String email);
	
	public Optional<User> findByStaffId(String search);
	public List<User> findByNameContainingIgnoreCase(String name);

	@Query("SELECT u.email FROM User u JOIN u.roles r WHERE r.name = :role")
	List<String> findEmailsByRole(String role);
}
