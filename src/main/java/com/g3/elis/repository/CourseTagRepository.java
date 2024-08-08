package com.g3.elis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.g3.elis.model.CourseTag;

@Repository
public interface CourseTagRepository extends JpaRepository<CourseTag, Integer>{

}
