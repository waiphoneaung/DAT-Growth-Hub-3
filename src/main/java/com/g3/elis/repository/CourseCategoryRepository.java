package com.g3.elis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g3.elis.model.CourseCategory;

public interface CourseCategoryRepository extends JpaRepository<CourseCategory, Integer> {

}
