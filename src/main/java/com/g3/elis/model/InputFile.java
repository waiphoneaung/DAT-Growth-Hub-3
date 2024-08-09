package com.g3.elis.model;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "files")
public class InputFile 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String fileName;
	private Timestamp createdAt;

	@ManyToOne
	@JoinColumn(name="course_materials_id")
	private CourseMaterial courseMaterials;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public Timestamp getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}


	public CourseMaterial getCourseMaterials() {
		return courseMaterials;
	}


	public void setCourseMaterials(CourseMaterial courseMaterials) {
		this.courseMaterials = courseMaterials;
	}


	public InputFile(int id, String fileName, Timestamp createdAt, CourseMaterial courseMaterials) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.createdAt = createdAt;
		this.courseMaterials = courseMaterials;
	}


	public InputFile() {
		super();
	}
	
		
	}
