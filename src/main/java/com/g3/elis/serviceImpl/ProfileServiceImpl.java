package com.g3.elis.serviceImpl;

import com.g3.elis.config.FileStorageConfig;
import com.g3.elis.dto.form.ProfileDto;
import com.g3.elis.model.Profile;
import com.g3.elis.model.User;
import com.g3.elis.repository.ProfileRepository;
import com.g3.elis.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private FileStorageConfig fileStorageConfig;

	@Override
	public Profile getProfileById(int id) {
		return profileRepository.findById(id).orElse(null);
	}

	@Override
	public void updateProfile(User user, ProfileDto profileDto) throws IOException {
		Profile profile = profileRepository.findProfileByUser(user);
		if (profile != null) {
			profile.setAddress(profileDto.getAddress());
			profile.setDescription(profileDto.getDescription());
			profile.setPhNo(profileDto.getPhNo());
			profile.setUser(user);

			Path targetLocation = fileStorageConfig.getProfileImageDir().resolve(profile.getProfileImg());
			if (Files.exists(targetLocation)) {
				fileStorageConfig.deleteProfileImageFile(profile.getProfileImg());
			}

			profile.setProfileImg(profileDto.getProfileImg().getOriginalFilename());

			MultipartFile profileImage = profileDto.getProfileImg();
			fileStorageConfig.saveProfileImageFile(profileImage, profileImage.getOriginalFilename());
			profileRepository.save(profile);
		}
	}

	@Override
	public void createProfile(User user, ProfileDto profileDto) throws IOException {
		Profile profile = new Profile();
		profile.setAddress(profileDto.getAddress());
		profile.setDescription(profileDto.getDescription());
		profile.setPhNo(profileDto.getPhNo());
		profile.setProfileImg(profileDto.getProfileImg().getOriginalFilename());
		profile.setUser(user);

		MultipartFile profileImage = profileDto.getProfileImg();
		fileStorageConfig.saveProfileImageFile(profileImage, profileImage.getOriginalFilename());

		profileRepository.save(profile);
	}

	@Override
	public Profile getProfileByUser(User user) {
		return profileRepository.findProfileByUser(user);
	}

	@Override
	public void updateStudentProfile(User user, ProfileDto profileDto) throws IOException {
		Profile profile = profileRepository.findProfileByUser(user);
		if (profile != null) {
			profile.setAddress(profileDto.getAddress());
			profile.setDescription(profileDto.getDescription());
			profile.setPhNo(profileDto.getPhNo());
			profile.setUser(user);

			Path targetLocation = fileStorageConfig.getProfileImageDir().resolve(profile.getProfileImg());
			if (Files.exists(targetLocation)) {
				fileStorageConfig.deleteProfileImageFile(profile.getProfileImg());
			}

			profile.setProfileImg(profileDto.getProfileImg().getOriginalFilename());

			MultipartFile profileImage = profileDto.getProfileImg();
			fileStorageConfig.saveProfileImageFile(profileImage, profileImage.getOriginalFilename());
			profileRepository.save(profile);
		}
	}
		
		
	

	@Override
	public void createStudentProfile(User user, ProfileDto profileDto) throws IOException {
		Profile profile = new Profile();
		profile.setAddress(profileDto.getAddress());
		profile.setDescription(profileDto.getDescription());
		profile.setPhNo(profileDto.getPhNo());
		profile.setProfileImg(profileDto.getProfileImg().getOriginalFilename());
		profile.setUser(user);

		MultipartFile profileImage = profileDto.getProfileImg();
		fileStorageConfig.saveProfileImageFile(profileImage, profileImage.getOriginalFilename());

		profileRepository.save(profile);
	}
	
	}

