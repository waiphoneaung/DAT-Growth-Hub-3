package com.g3.elis.model;

import java.sql.Timestamp;
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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="course_modules")
public class CourseModule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String moduleTitle;
	private String duration;
	private int maxScore;
	private int totalResultScore;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	
	@OneToMany(mappedBy = "courseModules", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CourseMaterial> courseMaterials;
	
	@OneToMany(mappedBy = "courseModules", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CourseAssignment> courseAssignment;
	
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course courses;
	
	@OneToOne(mappedBy = "course_modules")
	private ModuleGrade moduleGrade;

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

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public int getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}

	public int getTotalResultScore() {
		return totalResultScore;
	}

	public void setTotalResultScore(int totalResultScore) {
		this.totalResultScore = totalResultScore;
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

	public Course getCourses() {
		return courses;
	}

	public void setCourses(Course courses) {
		this.courses = courses;
	}

	public ModuleGrade getModuleGrade() {
		return moduleGrade;
	}

	public void setModuleGrade(ModuleGrade moduleGrade) {
		this.moduleGrade = moduleGrade;
	}

	public CourseModule(int id, String moduleTitle, String duration, int maxScore, int totalResultScore,
			Timestamp createdAt, Timestamp updatedAt, List<CourseMaterial> courseMaterials,
			List<CourseAssignment> courseAssignment, Course courses, ModuleGrade moduleGrade) {
		super();
		this.id = id;
		this.moduleTitle = moduleTitle;
		this.duration = duration;
		this.maxScore = maxScore;
		this.totalResultScore = totalResultScore;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.courseMaterials = courseMaterials;
		this.courseAssignment = courseAssignment;
		this.courses = courses;
		this.moduleGrade = moduleGrade;
	}
	public CourseModule()
	{
		
	}
}
