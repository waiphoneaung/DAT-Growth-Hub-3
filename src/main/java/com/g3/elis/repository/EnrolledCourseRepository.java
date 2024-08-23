package com.g3.elis.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import com.g3.elis.model.EnrolledCourse;
import com.g3.elis.model.User;

@Repository
public interface EnrolledCourseRepository extends JpaRepository<EnrolledCourse, Integer> {
	Page<EnrolledCourse> findByUsers(User user, Pageable pageable);
	
	Page<EnrolledCourse> findByCourses_CourseTitleContainingIgnoreCase(String keyword, Pageable pageable);
}
