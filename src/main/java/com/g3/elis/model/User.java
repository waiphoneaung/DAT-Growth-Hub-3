package com.g3.elis.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")

public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String division;
	private String staffId;
	private String name;
	private String doorLogNo;
	private String dept;
	private String team;
	private String email;
	private String status;
	private String password;
	private String gender;

	@Column(nullable = false)
	private boolean enabled;

	@ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
	private Set<Role> roles = new HashSet<>();

	@OneToMany(mappedBy = "users", fetch = FetchType.LAZY)
	private Set<Course> courses = new HashSet<>();

	@OneToOne(mappedBy = "user")
	private Profile profile;

	@OneToMany(mappedBy = "users", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<EnrolledCourse> enrolledCourses = new ArrayList<>();

	@OneToMany(mappedBy = "users", fetch = FetchType.LAZY)
	private Set<Report> reports = new HashSet<>();

	@OneToMany(mappedBy = "users", fetch = FetchType.LAZY)

	private Set<BlogPost> blogposts = new HashSet<>();

	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Forum> forums = new ArrayList<>();

	@OneToMany(mappedBy = "users", fetch = FetchType.LAZY)
	private List<ForumComment> forumComments = new ArrayList<>();

	@OneToMany(mappedBy = "users", fetch = FetchType.LAZY)
	private Set<CourseCategory> courseCategories = new HashSet<>();

	@OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
	private List<Achievement> achievement = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<UserLog> log = new ArrayList<>();

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Grade> grades = new ArrayList<>();



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getDivision() {
		return division;
	}



	public void setDivision(String division) {
		this.division = division;
	}



	public String getStaffId() {
		return staffId;
	}



	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDoorLogNo() {
		return doorLogNo;
	}



	public void setDoorLogNo(String doorLogNo) {
		this.doorLogNo = doorLogNo;
	}



	public String getDept() {
		return dept;
	}



	public void setDept(String dept) {
		this.dept = dept;
	}



	public String getTeam() {
		return team;
	}



	public void setTeam(String team) {
		this.team = team;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public boolean isEnabled() {
		return enabled;
	}



	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}



	public Set<Role> getRoles() {
		return roles;
	}



	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}



	public Set<Course> getCourses() {
		return courses;
	}



	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}



	public Profile getProfile() {
		return profile;
	}



	public void setProfile(Profile profile) {
		this.profile = profile;
	}



	public List<EnrolledCourse> getEnrolledCourses() {
		return enrolledCourses;
	}



	public void setEnrolledCourses(List<EnrolledCourse> enrolledCourses) {
		this.enrolledCourses = enrolledCourses;
	}



	public Set<Report> getReports() {
		return reports;
	}



	public void setReports(Set<Report> reports) {
		this.reports = reports;
	}



	public Set<BlogPost> getBlogposts() {
		return blogposts;
	}



	public void setBlogposts(Set<BlogPost> blogposts) {
		this.blogposts = blogposts;
	}



	public List<Forum> getForums() {
		return forums;
	}



	public void setForums(List<Forum> forums) {
		this.forums = forums;
	}



	public List<ForumComment> getForumComments() {
		return forumComments;
	}



	public void setForumComments(List<ForumComment> forumComments) {
		this.forumComments = forumComments;
	}



	public Set<CourseCategory> getCourseCategories() {
		return courseCategories;
	}



	public void setCourseCategories(Set<CourseCategory> courseCategories) {
		this.courseCategories = courseCategories;
	}



	public List<Achievement> getAchievement() {
		return achievement;
	}



	public void setAchievement(List<Achievement> achievement) {
		this.achievement = achievement;
	}



	public List<UserLog> getLog() {
		return log;
	}



	public void setLog(List<UserLog> log) {
		this.log = log;
	}



	public List<Grade> getGrades() {
		return grades;
	}



	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}



	public User(int id, String division, String staffId, String name, String doorLogNo, String dept, String team,
			String email, String status, String password, String gender, boolean enabled, Set<Role> roles,
			Set<Course> courses, Profile profile, List<EnrolledCourse> enrolledCourses, Set<Report> reports,
			Set<BlogPost> blogposts, List<Forum> forums, List<ForumComment> forumComments,
			Set<CourseCategory> courseCategories, List<Achievement> achievement, List<UserLog> log,
			List<Grade> grades) {
		super();
		this.id = id;
		this.division = division;
		this.staffId = staffId;
		this.name = name;
		this.doorLogNo = doorLogNo;
		this.dept = dept;
		this.team = team;
		this.email = email;
		this.status = status;
		this.password = password;
		this.gender = gender;
		this.enabled = enabled;
		this.roles = roles;
		this.courses = courses;
		this.profile = profile;
		this.enrolledCourses = enrolledCourses;
		this.reports = reports;
		this.blogposts = blogposts;
		this.forums = forums;
		this.forumComments = forumComments;
		this.courseCategories = courseCategories;
		this.achievement = achievement;
		this.log = log;
		this.grades = grades;
	}



	public User() {

	}
}
