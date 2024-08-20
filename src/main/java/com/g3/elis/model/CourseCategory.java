package com.g3.elis.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "course_categories")
public class CourseCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String categoryName;
	
	@OneToMany(mappedBy="courseCategories",fetch = FetchType.LAZY)
    private Set<Course> courses=new HashSet<>();
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User users;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public CourseCategory(int id, String categoryName, Set<Course> courses) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.courses = courses;
	}

	public CourseCategory() {
		// TODO Auto-generated constructor stub
	}

	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public CourseCategory(int id, String categoryName, Set<Course> courses, User users) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.courses = courses;
		this.users = users;
	}
	
}
