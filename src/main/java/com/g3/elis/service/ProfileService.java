package com.g3.elis.service;

import java.io.IOException;

import com.g3.elis.dto.form.ProfileDto;
import com.g3.elis.model.Profile;
import com.g3.elis.model.User;

public interface ProfileService {
	Profile getProfileById(int id);
	Profile getProfileByUser(User user);

	void updateProfile(User user, ProfileDto profileDto) throws IOException;
	void createProfile(User user,ProfileDto profileDto) throws IOException;
	
	void updateStudentProfile(User user, ProfileDto profileDto) throws IOException;
	void createStudentProfile(User user,ProfileDto profileDto) throws IOException;

}