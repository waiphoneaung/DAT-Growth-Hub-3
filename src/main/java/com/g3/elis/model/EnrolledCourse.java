package com.g3.elis.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
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
	private boolean completeStatus;
	private double progress;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User users;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course courses;
	
	@OneToMany(mappedBy = "enrolledCourses",fetch = FetchType.LAZY)
	private Set<Report> reports=new HashSet<>();
	
	@OneToMany(mappedBy = "enrolledCourse",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<EnrolledModule> enrolledModules;
	
	public EnrolledCourse()
	{
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getEnrolledAt() {
		return enrolledAt;
	}

	public void setEnrolledAt(Timestamp enrolledAt) {
		this.enrolledAt = enrolledAt;
	}

	public Timestamp getCompletedAt() {
		return completedAt;
	}

	public void setCompletedAt(Timestamp completedAt) {
		this.completedAt = completedAt;
	}

	public boolean isCompleteStatus() {
		return completeStatus;
	}

	public void setCompleteStatus(boolean completeStatus) {
		this.completeStatus = completeStatus;
	}

	public double getProgress() {
		return progress;
	}

	public void setProgress(double progress) {
		this.progress = progress;
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

	public Set<Report> getReports() {
		return reports;
	}

	public void setReports(Set<Report> reports) {
		this.reports = reports;
	}

	public List<EnrolledModule> getEnrolledModules() {
		return enrolledModules;
	}

	public void setEnrolledModules(List<EnrolledModule> enrolledModules) {
		this.enrolledModules = enrolledModules;
	}

	public EnrolledCourse(int id, Timestamp enrolledAt, Timestamp completedAt, boolean completeStatus, double progress,
			User users, Course courses, Set<Report> reports, List<EnrolledModule> enrolledModules) {
		super();
		this.id = id;
		this.enrolledAt = enrolledAt;
		this.completedAt = completedAt;
		this.completeStatus = completeStatus;
		this.progress = progress;
		this.users = users;
		this.courses = courses;
		this.reports = reports;
		this.enrolledModules = enrolledModules;
	}
}
