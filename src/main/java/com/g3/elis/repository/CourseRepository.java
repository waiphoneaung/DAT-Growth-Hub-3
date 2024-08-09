package com.g3.elis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.g3.elis.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

}
