package com.g3.elis.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.g3.elis.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    Page<Course> findAll(Pageable pageable);

	Page<Course> findByCourseTitleContainingIgnoreCase(String courseTitle, Pageable pageable);
	long count();
	List<Course> findByCourseCategoriesId(int categoryId);
}
