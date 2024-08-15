package com.g3.elis.controller.instructor;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.g3.elis.dto.form.ProfileDto;
import com.g3.elis.dto.form.UserDto;
import com.g3.elis.model.Profile;
import com.g3.elis.model.User;
import com.g3.elis.security.LoginUserDetail;
import com.g3.elis.service.ProfileService;
import com.g3.elis.service.UserService;

@Controller
@RequestMapping("/instructor")
public class InstructorEditProfileController {
	

	
		@Autowired
		private ProfileService profileService;

		@Autowired
		private UserService userService;

		@GetMapping("/instructor-edit-profile")
		public String instructorEditProfile(Model model, Authentication authentication) {
			LoginUserDetail loginUser = (LoginUserDetail) authentication.getPrincipal();
			User user = loginUser.getUser();
			UserDto userDto = new UserDto();
			if (profileService.getProfileByUser(user) == null) {
				ProfileDto profileDto = new ProfileDto();
				
				model.addAttribute("user", user);
				model.addAttribute("profileDto", profileDto);
			} else {
				ProfileDto profileDto = new ProfileDto();
				Profile profile = profileService.getProfileByUser(user);
				
				profileDto.setAddress(profile.getAddress());
				profileDto.setDescription(profile.getDescription());
				profileDto.setPhNo(profile.getPhNo());
				
				String imageName = profileService.getProfileByUser(user).getProfileImg();
				model.addAttribute("profileImg",imageName);
				model.addAttribute("user", user);
				model.addAttribute("profileDto", profileDto);
			}
			model.addAttribute("userDto", userDto);
			model.addAttribute("content", "instructor/instructor-edit-profile");
			return "/instructor/instructor-layout";
		}

		@PostMapping("/instructor-edit-profile")
		public String adminEditProfile(@ModelAttribute("profileDto") ProfileDto profileDto,
				@RequestParam(name = "profileImage",required = false) MultipartFile profileImage,
				Authentication authentication, BindingResult result, Model model) throws IOException {
			
			
			LoginUserDetail loginUser = (LoginUserDetail) authentication.getPrincipal();
			User user = loginUser.getUser();
			
			if (profileImage.isEmpty()) {
				
				result.addError(new FieldError("profileDto", "profileImage", "The image file is required"));
			}
			if(result.hasErrors())
			{
				model.addAttribute("user", user);
				model.addAttribute("content", "instructor/instructor-edit-profile");
				return "/instructor/instructor-layout";
			}
			profileDto.setProfileImg(profileImage);		
			if (profileService.getProfileByUser(user) == null) {
				profileService.createProfile(user, profileDto);
			} else {
				profileService.updateProfile(user, profileDto);
			}
			String imageName = profileService.getProfileByUser(user).getProfileImg();
			model.addAttribute("user", user);
			model.addAttribute("profileImg",imageName);
			model.addAttribute("content", "instructor/instructor-edit-profile");
			return "redirect:/instructor/instructor-edit-profile";
		}

		@PostMapping("/instructor-edit-profile/password-change")
		public String adminPasswordChange(@RequestParam("new-password") String newPassword,
				@RequestParam("current-password") String currentPassword,
				@RequestParam("confirm-password") String confirmPassword, Authentication authentication, Model model) {
			BCryptPasswordEncoder passEncode = new BCryptPasswordEncoder();
			LoginUserDetail loginUser = (LoginUserDetail) authentication.getPrincipal();
			User user = loginUser.getUser();
			if (!passEncode.matches(currentPassword, user.getPassword()) || !(newPassword.equals(confirmPassword))) {
				model.addAttribute("user", user);
				model.addAttribute("content", "instructor/instructor-edit-profile");
				return "/instructor/instructor-layout";
			}
			userService.changePassword(user, newPassword);
			return "redirect:/sign-out";
		}
	}



