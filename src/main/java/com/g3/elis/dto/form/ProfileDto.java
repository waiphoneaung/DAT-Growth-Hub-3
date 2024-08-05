package com.g3.elis.dto.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProfileDto {
	

    @NotBlank(message = "Profile image is required")
    @Size(max = 255, message = "Profile image must be less than 255 characters")
    private String profileImg;

    @NotBlank(message = "Description is required")
    @Size(max = 255, message = "Description must be less than 255 characters")
    private String description;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address must be less than 255 characters")
    private String address;

    @NotBlank(message = "Phone number is required")
    @Size(max = 25, message = "Phone number must be less than 25 characters")
    private String phNo;

    @NotNull(message = "User ID is required")
    private int user_id;

    public ProfileDto() {
    }

    public ProfileDto(String profileImg, String description, String address, String phNo, int user_id) {
        
        this.profileImg = profileImg;
        this.description = description;
        this.address = address;
        this.phNo = phNo;
        this.user_id = user_id;
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

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

    

}
