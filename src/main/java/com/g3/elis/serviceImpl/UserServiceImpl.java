package com.g3.elis.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.g3.elis.dto.form.UserDto;
import com.g3.elis.model.Role;
import com.g3.elis.model.User;
import com.g3.elis.repository.RoleRepository;
import com.g3.elis.repository.UserRepository;
import com.g3.elis.service.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public User getUserById(int id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public void createUser(UserDto userDto) {
		User user = new User();
		
		if(userDto.getEmail() == null)
		{
			user.setEmail(userDto.getName().toLowerCase().replaceAll("\\s+", "") + "@diracetechnology.com");
		}
		else
		{
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
		
//		Role userRole = roleRepository.findById(2).orElseThrow(() -> new RuntimeException("Role is not found!"));
		Role userRole = roleRepository.findByName("ROLE_STUDENT").orElseThrow(() -> new RuntimeException("Role is not found!"));
		
		user.getRoles().add(userRole);
		userRole.getUsers().add(user);
		
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
		
	        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	        user.setEnabled(enabled);
	        user.setStatus(enabled ? "Active" : "Unactive");
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
		// TODO Auto-generated method stub
		return userRepository.findByRole("ROLE_INSTRUCTOR", pageable);
	}

	@Override
	public Page<User> searchInstructors(String name, String staffId, String dept, String division, Pageable pageable) {
		// TODO Auto-generated method stub
		return userRepository.searchInstructors(name, staffId, dept, division,pageable);
	}
	}
	
	
