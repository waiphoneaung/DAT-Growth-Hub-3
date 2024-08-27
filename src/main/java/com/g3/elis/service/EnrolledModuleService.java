package com.g3.elis.service;

import com.g3.elis.model.EnrolledModule;

public interface EnrolledModuleService {
	public void setStatusToTrue(int enrolledModuleId);
	// Method to save an EnrolledModule
    void save(EnrolledModule enrolledModule);
    
    // Method to reset complete status to false by module ID
    void setStatusToFalse(int moduleId);
}
