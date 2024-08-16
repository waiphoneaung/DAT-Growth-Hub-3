package com.g3.elis.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

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
@Table(name="enrolled_courses")
public class EnrolledCourse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Timestamp enrolledAt;
	private Timestamp completedAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User users;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course courses;
	
	@OneToMany(mappedBy = "enrolledCourses",fetch = FetchType.LAZY)
	 private Set<Report> reports=new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EnrolledCourse(int id, Timestamp enrolledAt, Timestamp completedAt, User users, Course courses) {
		super();
		this.id = id;
		this.enrolledAt = enrolledAt;
		this.completedAt = completedAt;
		this.users = users;
		this.courses = courses;
	}

	public Timestamp getCompletedAt() {
		return completedAt;
	}

	public void setCompletedAt(Timestamp completedAt) {
		this.completedAt = completedAt;
	}

	public Timestamp getEnrolledAt() {
		return enrolledAt;
	}

	public void setEnrolledAt(Timestamp enrolledDate) {
		this.enrolledAt = enrolledDate;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public Course getCourses() {
		return courses;
	}

	public void setCourses(Course courses) {
		this.courses = courses;
	}
	
	public EnrolledCourse()
	{
		
	}
}
