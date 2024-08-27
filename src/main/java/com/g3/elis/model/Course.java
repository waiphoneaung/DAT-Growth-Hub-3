package com.g3.elis.model;

import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "courses")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Size(max = 500)
	private String courseTitle;

	@Size(max = 2000)
	private String courseDescription;

	@Lob
	@Column(length = 50000)
	private String courseInfo;

	private String status;
	private Timestamp createdAt;
	private int duration;
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

	@OneToMany(mappedBy = "courses", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<EnrolledCourse> enrolledCourses = new ArrayList<>();

	@OneToMany(mappedBy = "courses", fetch = FetchType.LAZY)
	private List<Report> reports = new ArrayList<>();

	@OneToMany(mappedBy = "courses", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CourseModule> courseModule = new ArrayList<>();

	@OneToOne(mappedBy = "courses", fetch = FetchType.LAZY)
	private Achievement achievements;

	public Course() {

	}

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

	public String getCourseInfo() {
		return courseInfo;
	}

	public void setCourseInfo(String courseInfo) {
		this.courseInfo = courseInfo;
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

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
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

	public List<EnrolledCourse> getEnrolledCourses() {
		return enrolledCourses;
	}

	public void setEnrolledCourses(List<EnrolledCourse> enrolledCourses) {
		this.enrolledCourses = enrolledCourses;
	}

	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	public List<CourseModule> getCourseModule() {
		return courseModule;
	}

	public void setCourseModule(List<CourseModule> courseModule) {
		this.courseModule = courseModule;
	}

	public Achievement getAchievements() {
		return achievements;
	}

	public void setAchievements(Achievement achievements) {
		this.achievements = achievements;
	}

	public Course(int id, @Size(max = 500) String courseTitle, @Size(max = 2000) String courseDescription,
			String courseInfo, String status, Timestamp createdAt, int duration, String courseImageFileName,
			Timestamp updatedDate, User users, CourseCategory courseCategories, CourseTag courseTags,
			List<EnrolledCourse> enrolledCourses, List<Report> reports, List<CourseModule> courseModule,
			Achievement achievements) {
		super();
		this.id = id;
		this.courseTitle = courseTitle;
		this.courseDescription = courseDescription;
		this.courseInfo = courseInfo;
		this.status = status;
		this.createdAt = createdAt;
		this.duration = duration;
		this.courseImageFileName = courseImageFileName;
		this.updatedDate = updatedDate;
		this.users = users;
		this.courseCategories = courseCategories;
		this.courseTags = courseTags;
		this.enrolledCourses = enrolledCourses;
		this.reports = reports;
		this.courseModule = courseModule;
		this.achievements = achievements;
	}

	public boolean isCourseAssignmentPresent() {
		for (CourseModule tmpCourseModule : this.courseModule) {
			if (tmpCourseModule.getCourseAssignment() == null || tmpCourseModule.getCourseAssignment().size() == 0) {
				return false;
			}
		}
		return true;
	}

	public String getHtmlFileName() {
		// TODO Auto-generated method stub
		return null;
	}

	public Path gethtmlFileName() {
		// TODO Auto-generated method stub
		return null;
	}
}
