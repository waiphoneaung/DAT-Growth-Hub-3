package com.g3.elis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="profiles")
public class Profile {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String profileImg;
	private String description;
	private String address;
	private String phNo;
	
	@OneToOne
    @JoinColumn(name = "user_id")
    private User users;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhNo() {
		return phNo;
	}

	public void setPhNo(String phNo) {
		this.phNo = phNo;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public Profile(int id, String profileImg, String description, String address, String phNo, User users) {
		super();
		this.id = id;
		this.profileImg = profileImg;
		this.description = description;
		this.address = address;
		this.phNo = phNo;
		this.users = users;
	}

	public Object getFullName() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getPhoneNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setFullName(Object fullName) {
		// TODO Auto-generated method stub
		
	}

	public void setEmail(Object email) {
		// TODO Auto-generated method stub
		
	}

	public void setPhoneNumber(Object phoneNumber) {
		// TODO Auto-generated method stub
		
	}

	public Object getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setLocation(Object location) {
		// TODO Auto-generated method stub
		
	}

	public Object getAboutMe() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setAboutMe(Object aboutMe) {
		// TODO Auto-generated method stub
		
	}

	public void setProfilePicturePath(String profilePicturePath) {
		// TODO Auto-generated method stub
		
	}

	public void setPassword(String newPassword) {
		// TODO Auto-generated method stub
		
	}

	

	
}
