package com.g3.elis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.g3.elis.model.EnrolledMaterial;

@Repository
public interface EnrolledMaterialRepository extends JpaRepository<EnrolledMaterial,Integer>{

}
