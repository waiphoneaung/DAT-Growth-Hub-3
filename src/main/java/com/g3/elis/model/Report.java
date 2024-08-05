package com.g3.elis.model;

import java.sql.Timestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="report")
public class Report {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String reportType;
	private Timestamp createdAt;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User users;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="course_id")
	private Course courses;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="enrolled_course_id")
	private EnrolledCourse enrolledCourses;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
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

	public EnrolledCourse getEnrolledCourses() {
		return enrolledCourses;
	}

	public void setEnrolledCourses(EnrolledCourse enrolledCourses) {
		this.enrolledCourses = enrolledCourses;
	}

	public Report(int id, String reportType, Timestamp createdAt, User users, Course courses,
			EnrolledCourse enrolledCourses) {
		super();
		this.id = id;
		this.reportType = reportType;
		this.createdAt = createdAt;
		this.users = users;
		this.courses = courses;
		this.enrolledCourses = enrolledCourses;
	}
	
}
