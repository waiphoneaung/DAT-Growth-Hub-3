package com.g3.elis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.g3.elis.model.CourseAssignment;

@Repository
public interface CourseAssignmentReopsitory extends JpaRepository<CourseAssignment, Integer> {

}
