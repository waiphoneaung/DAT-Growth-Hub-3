package com.g3.elis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="enrolled_materials")
public class EnrolledMaterial 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private double progress;
	private boolean completeStatus;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "enrolled_module_id")
	private EnrolledModule enrolledModule;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_material_id")
	private CourseMaterial courseMaterial;
	
	public EnrolledMaterial()
	{
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getProgress() {
		return progress;
	}

	public void setProgress(double progress) {
		this.progress = progress;
	}

	public boolean isCompleteStatus() {
		return completeStatus;
	}

	public void setCompleteStatus(boolean completeStatus) {
		this.completeStatus = completeStatus;
	}

	public EnrolledModule getEnrolledModule() {
		return enrolledModule;
	}

	public void setEnrolledModule(EnrolledModule enrolledModule) {
		this.enrolledModule = enrolledModule;
	}

	public CourseMaterial getCourseMaterial() {
		return courseMaterial;
	}

	public void setCourseMaterial(CourseMaterial courseMaterial) {
		this.courseMaterial = courseMaterial;
	}

	public EnrolledMaterial(int id, double progress, boolean completeStatus, EnrolledModule enrolledModule,
			CourseMaterial courseMaterial) {
		super();
		this.id = id;
		this.progress = progress;
		this.completeStatus = completeStatus;
		this.enrolledModule = enrolledModule;
		this.courseMaterial = courseMaterial;
	}
	
}
