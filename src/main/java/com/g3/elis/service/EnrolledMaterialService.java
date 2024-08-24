package com.g3.elis.service;

import com.g3.elis.model.EnrolledMaterial;

public interface EnrolledMaterialService{
	void setStatusToTrue(int enrollMaterialId);
    // Method to reset the status of materials related to a specific course
    void resetMaterialsStatusByCourse(int courseId);
	EnrolledMaterial getEnrolledMaterialByEnrolledMaterialId(int enrolledMaterialId);
}
