package com.g3.elis.controller.admin;

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
@RequestMapping("/admin")
public class AdminEditProfileController {

	@Autowired
	private ProfileService profileService;

	@Autowired
	private UserService userService;

	@GetMapping("/admin-edit-profile")
	public String adminEditProfile(Model model, Authentication authentication) {
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

			String imageName =  profileService.getProfileByUser(user).getProfileImg();
			model.addAttribute("profileImg", imageName);
			model.addAttribute("user", user);
			model.addAttribute("profileDto", profileDto);
		}
		model.addAttribute("userDto", userDto);
		model.addAttribute("content", "admin/admin-edit-profile");
		return "/admin/admin-layout";
	}

}