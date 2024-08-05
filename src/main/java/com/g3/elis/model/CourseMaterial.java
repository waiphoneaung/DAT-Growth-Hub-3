package com.g3.elis.model;

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
import jakarta.persistence.Table;

@Entity
@Table(name="course_materials")
public class CourseMaterial {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "status", nullable = false)
	private boolean status;
	
	@Column(name = "duration", nullable = false)
	private String duration;
	
	@Column(name = "content", length = 255)
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "course_module_id", nullable = false)
	private CourseModule courseModules;
	
	@OneToMany(mappedBy = "courseMaterials", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<InputFile> inputFiles;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public CourseModule getCourseModules() {
		return courseModules;
	}

	public void setCourseModules(CourseModule courseModules) {
		this.courseModules = courseModules;
	}

	public List<InputFile> getInputFiles() {
		return inputFiles;
	}

	public void setInputFiles(List<InputFile> inputFiles) {
		this.inputFiles = inputFiles;
	}

	public CourseMaterial(int id, String title, boolean status, String duration, String content,
			CourseModule courseModules, List<InputFile> inputFiles) {
		super();
		this.id = id;
		this.title = title;
		this.status = status;
		this.duration = duration;
		this.content = content;
		this.courseModules = courseModules;
		this.inputFiles = inputFiles;
	}

	public CourseMaterial() {
		super();
	}
	
	
	
}
