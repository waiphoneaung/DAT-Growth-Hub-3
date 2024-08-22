package com.g3.elis.service;

import com.g3.elis.model.EnrolledMaterial;

public interface EnrolledMaterialService{
	void setStatusToTrue(int enrollMaterialId);
	EnrolledMaterial getEnrolledMaterialByEnrolledMaterialId(int enrolledMaterialId);
}
