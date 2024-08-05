package com.g3.elis.service;

import com.g3.elis.model.Profile;

public interface ProfileService {
	
	    
	    Profile updateProfile(int userId, Profile updatedProfile);
	    void updateProfilePicture(int userId, String profilePicturePath);
	    void changePassword(int userId, String newPassword);
		Profile getProfileByUserId(int userId);
		Profile getProfileByUserId(Long userId);
		Profile updateProfile(Long userId, Profile updatedProfile);
		void updateProfilePicture(Long userId, String profilePicturePath);
		void changePassword(Long userId, String newPassword);
		
	}



