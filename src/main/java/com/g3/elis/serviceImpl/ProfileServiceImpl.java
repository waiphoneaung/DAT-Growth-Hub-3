package com.g3.elis.serviceImpl;

import com.g3.elis.service.ProfileService;
import com.g3.elis.model.Profile;
import com.g3.elis.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Profile getProfileByUserId(Long userId) {
        return profileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found for user ID: " + userId));
    }

    @Override
    public Profile updateProfile(Long userId, Profile updatedProfile) {
        Profile existingProfile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found for user ID: " + userId));
        
        existingProfile.setFullName(updatedProfile.getFullName());
        existingProfile.setEmail(updatedProfile.getEmail());
        existingProfile.setPhoneNumber(updatedProfile.getPhoneNumber());
        existingProfile.setLocation(updatedProfile.getLocation());
        existingProfile.setAboutMe(updatedProfile.getAboutMe());
        // Update other fields as necessary

        return profileRepository.save(existingProfile);
    }

    @Override
    public void updateProfilePicture(Long userId, String profilePicturePath) {
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found for user ID: " + userId));
        
        profile.setProfilePicturePath(profilePicturePath);
        profileRepository.save(profile);
    }

    @Override
    public void changePassword(Long userId, String newPassword) {
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found for user ID: " + userId));
        
        // Here you should use a password encoder to hash the new password
        profile.setPassword(newPassword); // Assume setPassword handles encoding
        profileRepository.save(profile);
    }

	@Override
	public Profile getProfileByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Profile updateProfile(int userId, Profile updatedProfile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateProfilePicture(int userId, String profilePicturePath) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changePassword(int userId, String newPassword) {
		// TODO Auto-generated method stub
		
	}
}
