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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "courses")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String courseTitle;
	private String courseDescription;
	private String status;
	private Timestamp createdAt;
	private String duration;
	private boolean courseCompletedStatus;
	private double progress;
	private String courseImageFileName;
	private Timestamp updatedDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User users;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_category_id")
	private CourseCategory courseCategories;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_tag_id")
	private CourseTag courseTags;

	@OneToMany(mappedBy = "courses", fetch = FetchType.LAZY)
	private Set<EnrolledCourse> enrolledCourses = new HashSet<>();

	@OneToMany(mappedBy = "courses", fetch = FetchType.LAZY)
	private Set<Report> reports = new HashSet<>();

	@OneToMany(mappedBy = "courses", fetch = FetchType.LAZY)
	private Set<CourseModule> courseModule = new HashSet<>();

	@OneToOne(mappedBy = "courses", fetch = FetchType.LAZY)
	private Achievement achievements;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public boolean isCourseCompletedStatus() {
		return courseCompletedStatus;
	}

	public void setCourseCompletedStatus(boolean courseCompletedStatus) {
		this.courseCompletedStatus = courseCompletedStatus;
	}

	public double getProgress() {
		return progress;
	}

	public void setProgress(double progress) {
		this.progress = progress;
	}

	public String getCourseImageFileName() {
		return courseImageFileName;
	}

	public void setCourseImageFileName(String courseImageFileName) {
		this.courseImageFileName = courseImageFileName;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public CourseCategory getCourseCategories() {
		return courseCategories;
	}

	public void setCourseCategories(CourseCategory courseCategories) {
		this.courseCategories = courseCategories;
	}

	public CourseTag getCourseTags() {
		return courseTags;
	}

	public void setCourseTags(CourseTag courseTags) {
		this.courseTags = courseTags;
	}

	public Set<EnrolledCourse> getEnrolledCourses() {
		return enrolledCourses;
	}

	public void setEnrolledCourses(Set<EnrolledCourse> enrolledCourses) {
		this.enrolledCourses = enrolledCourses;
	}

	public Course(int id, String courseTitle, String courseDescription, String status, Timestamp createdAt,
			String duration, boolean courseCompletedStatus, double progress, String courseImageFileName,
			Timestamp updatedDate, User users, CourseCategory courseCategories, CourseTag courseTags,
			Set<EnrolledCourse> enrolledCourses) {
		super();
		this.id = id;
		this.courseTitle = courseTitle;
		this.courseDescription = courseDescription;
		this.status = status;
		this.createdAt = createdAt;
		this.duration = duration;
		this.courseCompletedStatus = courseCompletedStatus;
		this.progress = progress;
		this.courseImageFileName = courseImageFileName;
		this.updatedDate = updatedDate;
		this.users = users;
		this.courseCategories = courseCategories;
		this.courseTags = courseTags;
		this.enrolledCourses = enrolledCourses;
	}
}
