package com.g3.elis.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "enrolled_modules")
public class EnrolledModule 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private double progress;
	private boolean completeStatus;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "enrolled_course_module_id")
	private EnrolledCourse enrolledCourse;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_module_id")
	private CourseModule courseModule;
	
	@OneToMany(mappedBy = "enrolledModule", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<EnrolledAssignment> enrolledAssignment;
	
	@OneToMany(mappedBy = "enrolledModule", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<EnrolledMaterial> enrolledMaterial;

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

	public EnrolledCourse getEnrolledCourse() {
		return enrolledCourse;
	}

	public void setEnrolledCourse(EnrolledCourse enrolledCourse) {
		this.enrolledCourse = enrolledCourse;
	}

	public CourseModule getCourseModule() {
		return courseModule;
	}

	public void setCourseModule(CourseModule courseModule) {
		this.courseModule = courseModule;
	}
	
	public List<EnrolledAssignment> getEnrolledAssignment() {
		return enrolledAssignment;
	}

	public void setEnrolledAssignment(List<EnrolledAssignment> enrolledAssignment) {
		this.enrolledAssignment = enrolledAssignment;
	}

	public List<EnrolledMaterial> getEnrolledMaterial() {
		return enrolledMaterial;
	}

	public void setEnrolledMaterial(List<EnrolledMaterial> enrolledMaterial) {
		this.enrolledMaterial = enrolledMaterial;
	}

	public EnrolledModule(int id, double progress, boolean completeStatus, EnrolledCourse enrolledCourse,
			CourseModule courseModule, List<EnrolledAssignment> enrolledAssignment,
			List<EnrolledMaterial> enrolledMaterial) {
		super();
		this.id = id;
		this.progress = progress;
		this.completeStatus = completeStatus;
		this.enrolledCourse = enrolledCourse;
		this.courseModule = courseModule;
		this.enrolledAssignment = enrolledAssignment;
		this.enrolledMaterial = enrolledMaterial;
	}

	public EnrolledModule()
	{
		
	}
	
	public int enrolledMaterialAndAssignmentCompleteCount()
	{
		int count = 0;
		for(EnrolledMaterial enrolledMaterial : this.enrolledMaterial)
		{
			if(enrolledMaterial.isCompleteStatus() == true) count++;
		}
		
		for(EnrolledAssignment enrolledAssignment : this.enrolledAssignment)
		{
			if(enrolledAssignment.isCompleteStatus() == true) count++;
		}
		
		return count;
	}
	
	public double calculateProgress()
	{
		double index = (double)((double)(enrolledMaterialAndAssignmentCompleteCount()) / ((double)(courseModule.getCourseMaterials().size() + courseModule.getCourseAssignment().size()))) * 100;
		return index;
	}
}
