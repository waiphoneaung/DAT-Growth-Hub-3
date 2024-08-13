package com.g3.elis.dto.form;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Size;

public class ProfileDto {

	private MultipartFile profileImg;

	@Size(max = 255, message = "Description must be less than 255 characters")
	private String description;

	@Size(max = 255, message = "Address must be less than 255 characters")
	private String address;

	@Size(max = 15, message = "Phone number must be less than 15 characters")
	private String phNo;

	private int user_id;

	public ProfileDto() {
	}

	public MultipartFile getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(MultipartFile profileImg) {
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

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public ProfileDto(MultipartFile profileImg,
			@Size(max = 255, message = "Description must be less than 255 characters") String description,
			@Size(max = 255, message = "Address must be less than 255 characters") String address,
			@Size(max = 15, message = "Phone number must be less than 15 characters") String phNo, int user_id) {
		super();
		this.profileImg = profileImg;
		this.description = description;
		this.address = address;
		this.phNo = phNo;
		this.user_id = user_id;
	}

}
