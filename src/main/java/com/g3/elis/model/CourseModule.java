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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="course_modules")
public class CourseModule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="module_title", nullable = false, length = 255)
	private String moduleTitle;
	
	@Column(name = "duration", nullable = false, length = 50)
	private String duration;
	
	@Column(name = "max_score", nullable = false)
	private int maxScore;
	
	@Column(name = "total_result_score")
	private int totalResultScore;
	
	@Column(name = "created_at", nullable = false)
	private Timestamp createdAt;
	
	@Column(name = "updated_at")
	private Timestamp updatedAt;
	
	@OneToMany(mappedBy = "courseModules", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CourseMaterial> courseMaterials;
	
	@OneToMany(mappedBy = "courseModules", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CourseAssignment> courseAssessments;
	
//	@ManyToOne
//	@JoinColumn(name = "course_id")
//	private Course course;
	
	
}
