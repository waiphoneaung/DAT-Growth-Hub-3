package com.g3.elis.model;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "course_modules")
public class CourseModule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 1000)
	private String moduleTitle;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	@OneToMany(mappedBy = "courseModules", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<CourseMaterial> courseMaterials;

	@OneToMany(mappedBy = "courseModules", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<CourseAssignment> courseAssignment;

	@OneToMany(mappedBy = "courseModule", fetch = FetchType.LAZY)
	private List<EnrolledModule> enrolledModule;

	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course courses;

	public CourseModule(int id, String moduleTitle, Timestamp createdAt, Timestamp updatedAt,
			List<CourseMaterial> courseMaterials, List<CourseAssignment> courseAssignment,
			List<EnrolledModule> enrolledModule, Course courses) {
		super();
		this.id = id;
		this.moduleTitle = moduleTitle;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.courseMaterials = courseMaterials;
		this.courseAssignment = courseAssignment;
		this.enrolledModule = enrolledModule;
		this.courses = courses;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModuleTitle() {
		return moduleTitle;
	}

	public void setModuleTitle(String moduleTitle) {
		this.moduleTitle = moduleTitle;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<CourseMaterial> getCourseMaterials() {
		return courseMaterials;
	}

	public void setCourseMaterials(List<CourseMaterial> courseMaterials) {
		this.courseMaterials = courseMaterials;
	}

	public List<CourseAssignment> getCourseAssignment() {
		return courseAssignment;
	}

	public void setCourseAssignment(List<CourseAssignment> courseAssignment) {
		this.courseAssignment = courseAssignment;
	}

	public List<EnrolledModule> getEnrolledModule() {
		return enrolledModule;
	}

	public void setEnrolledModule(List<EnrolledModule> enrolledModule) {
		this.enrolledModule = enrolledModule;
	}

	public Course getCourses() {
		return courses;
	}

	public void setCourses(Course courses) {
		this.courses = courses;
	}

	public CourseModule() {

	}
}
