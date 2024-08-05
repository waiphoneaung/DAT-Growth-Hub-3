package com.g3.elis.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "course_tags")
public class CourseTag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int id;
	private String tagName;

	@OneToMany(mappedBy = "courseTags", fetch = FetchType.LAZY)
	private Set<Course> courses = new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public CourseTag(int id, String tagName, Set<Course> courses) {
		super();
		this.id = id;
		this.tagName = tagName;
		this.courses = courses;
	}

}
