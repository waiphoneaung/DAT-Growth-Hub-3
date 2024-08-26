package com.g3.elis.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.g3.elis.dto.form.UserDto;
import com.g3.elis.model.Course;
import com.g3.elis.model.CourseCategory;
import com.g3.elis.model.Role;
import com.g3.elis.model.User;
import com.g3.elis.repository.CourseRepository;
import com.g3.elis.repository.EnrolledCourseRepository;
import com.g3.elis.repository.RoleRepository;
import com.g3.elis.repository.UserRepository;
import com.g3.elis.security.LoginUserDetail;
import com.g3.elis.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private CourseRepository courseRepository;

	@Override
	public User getUserById(int id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public void createUser(UserDto userDto) {
		
		Optional<User> existingUser = userRepository.findByStaffId(userDto.getStaffId());
	    if (existingUser.isPresent()) {
	        return;
	    }
		
		

		
		User user = new User();	
		
		if(userDto.getEmail() == null)
		{
			user.setEmail(userDto.getName().toLowerCase().replaceAll("\\s+", "") + "@diracetechnology.com");
		} else {
			user.setEmail(userDto.getEmail());
		}

		user.setName(userDto.getName());
		user.setDivision(userDto.getDivision());
		user.setStaffId(userDto.getStaffId());
		user.setDoorLogNo(userDto.getDoorLogNo());
		user.setDept(userDto.getDept());
		user.setTeam(userDto.getTeam());
		user.setStatus(userDto.getStatus());
		user.setGender(userDto.getGender());

		user.setEnabled(true);
		user.setPassword(new BCryptPasswordEncoder().encode("dirace@1234"));

		if (userDto.getRole() != null) {
			String roleName = userDto.getRole().equalsIgnoreCase("Admin") ? "ROLE_ADMIN"
					: userDto.getRole().equalsIgnoreCase("Instructor") ? "ROLE_INSTRUCTOR"
							: userDto.getRole().equalsIgnoreCase("Student") ? "ROLE_STUDENT" : "";
			Role userRole = roleRepository.findByName(roleName)
					.orElseThrow(() -> new RuntimeException("Role is not found"));
			userRole.getUsers().add(user);
			user.getRoles().add(userRole);
			userRepository.save(user);
			return;
		}
		Role userRole = roleRepository.findByName("ROLE_STUDENT")
				.orElseThrow(() -> new RuntimeException("Role is not found!"));
		userRole.getUsers().add(user);
		user.getRoles().add(userRole);
		userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public List<String> getEmailsByRole(String role) {
		return userRepository.findEmailsByRole(role);
	}


	@Override
	public void updateUserStatus(int id, boolean enabled) {

		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		user.setEnabled(enabled);
		user.setStatus(enabled ? "Active" : "Inactive");
		userRepository.save(user);
	}

	@Override
	public Page<User> getAllStudents(Pageable pageable) {
		return userRepository.findByRole("ROLE_STUDENT", pageable);
	}

	@Override
	public Page<User> searchUsersByName(String name, Pageable pageable) {
		return userRepository.findByNameContainingIgnoreCaseAndRole(name, "ROLE_STUDENT", pageable);
	}

	@Override
	public Page<User> getAllInstructors(Pageable pageable) {
		
        return userRepository.findByRole("ROLE_INSTRUCTOR", pageable);
	}

	@Override
	public Page<User> searchInstructors(String name, String staffId, String dept, String division, Pageable pageable) {
		// TODO Auto-generated method stub

		return userRepository.searchInstructors(name, staffId, dept, division, pageable);
	}

	public void changePassword(User user, String newPassword) {
		user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
		userRepository.save(user);
	}

	@Override
	public User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		LoginUserDetail userDetail = (LoginUserDetail) authentication.getPrincipal();
		User user = userDetail.getUser();
		return user;
	}

	@Override
	public boolean isUserEnrolled(String username, Long courseId) {
	    
	    return userRepository.existsByNameAndId(username, courseId);
	}

	@Override
	public long countAllInstructor() {
		
	        return userRepository.count("ROLE_INSTRUCTOR");
	   
	}

	@Override
	public long countAllStudent() {
		return userRepository.count("ROLE_STUDENT");
	}

	
	
	
	


	




}
