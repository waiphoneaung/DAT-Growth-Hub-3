package com.g3.elis.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g3.elis.model.EnrolledMaterial;
import com.g3.elis.repository.EnrolledMaterialRepository;
import com.g3.elis.service.EnrolledMaterialService;

@Service
public class EnrolledMaterialServiceImpl implements EnrolledMaterialService {

	@Autowired
	private EnrolledMaterialRepository enrolledMaterialRepository;

	@Override
	public void setStatusToTrue(int enrollMaterialId) {
		EnrolledMaterial enrolledMaterial = enrolledMaterialRepository.findById(enrollMaterialId).orElse(null);
		enrolledMaterial.setCompleteStatus(true);
		enrolledMaterialRepository.save(enrolledMaterial);
	}

	@Override
	public void resetMaterialsStatusByCourse(int courseId) {
		// Fetch all materials associated with the course and reset their status
		enrolledMaterialRepository.updateStatusByEnrolledCourseId(courseId, false);
	}

	public EnrolledMaterial getEnrolledMaterialByEnrolledMaterialId(int enrolledMaterialId) {
		return enrolledMaterialRepository.findById(enrolledMaterialId).orElse(null);
	}

}
