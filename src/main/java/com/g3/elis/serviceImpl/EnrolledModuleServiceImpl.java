package com.g3.elis.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g3.elis.model.EnrolledAssignment;
import com.g3.elis.model.EnrolledMaterial;
import com.g3.elis.model.EnrolledModule;
import com.g3.elis.repository.EnrolledModuleRepository;
import com.g3.elis.service.EnrolledModuleService;

@Service
public class EnrolledModuleServiceImpl implements EnrolledModuleService{

	@Autowired
	private EnrolledModuleRepository enrolledModuleRepository;
	
	@Override
	public void setStatusToTrue(int enrolledModuleId) 
	{
		EnrolledModule enrolledModule = enrolledModuleRepository.findById(enrolledModuleId).orElse(null);
		while(true)
		{
			for(EnrolledAssignment enrolledAssignment : enrolledModule.getEnrolledAssignment())
			{
				if(enrolledAssignment.isCompleteStatus() != true) return;
			}
			for(EnrolledMaterial enrolledMaterial : enrolledModule.getEnrolledMaterial())
			{
				if(enrolledMaterial.isCompleteStatus() != true) return;
			}
			enrolledModule.setCompleteStatus(true);
			enrolledModuleRepository.save(enrolledModule);
			return;
		}
		
	}

	@Override
	public void save(EnrolledModule enrolledModule) {
		 enrolledModuleRepository.save(enrolledModule);
		
	}

	@Override
	public void setStatusToFalse(int moduleId) {
		 EnrolledModule enrolledModule = enrolledModuleRepository.findById(moduleId).orElse(null);
	        if (enrolledModule != null) {
	            enrolledModule.setCompleteStatus(false);
	            enrolledModuleRepository.save(enrolledModule);
	        }
	    }
		
	}


