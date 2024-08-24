package com.g3.elis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.g3.elis.model.EnrolledMaterial;

import jakarta.transaction.Transactional;

@Repository
public interface EnrolledMaterialRepository extends JpaRepository<EnrolledMaterial,Integer>{
	
	@Transactional
    @Modifying
    @Query("UPDATE EnrolledMaterial em \r\n"
    		+ "SET em.completeStatus = :status \r\n"
    		+ "WHERE em.enrolledModule.enrolledCourse.id = :courseId\r\n"
    		+ "")
    void updateStatusByEnrolledCourseId(@Param("courseId") int courseId, @Param("status") boolean status);

}
