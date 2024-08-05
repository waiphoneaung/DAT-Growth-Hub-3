package com.g3.elis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g3.elis.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

}
